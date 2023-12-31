package org.wjy.gameforu.admin2.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.model.entity.Game;
import org.wjy.gameforu.admin2.service.GameService;
import org.wjy.gameforu.admin2.service.GenreService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.external.SteamGame;
import org.wjy.gameforu.mq.constant.MqConst;
import org.wjy.gameforu.mq.service.RabbitService;
import org.wjy.gameforu.vo.GameIdsVo;
import org.wjy.gameforu.vo.GameQueryVo;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/game/admin")
@Slf4j
@Api(tags = "game management")
public class GameController {

    /**
     * inject service
     */
    @Autowired
    private GameService gameService;

    //线程池
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private GenreService genreService;

    @Autowired
    RabbitService rabbitService;

    @ApiOperation("get game genres")
    @GetMapping("/gamegenre/{gid}")
    public Result gemeGenre(@PathVariable Integer gid){
        Map<String,Object> map =  genreService.getGenreByGameId(gid);
        return Result.ok(map);
    }

    /**
     * set genre for specific game
     * @param gid game id
     * @param genids genre id list
     * @return
     */
    @ApiOperation("set game genres")
    @PostMapping("/setGamegenre")
    public Result setGameGenre(@RequestParam Integer gid,
                               @RequestParam(required = false) Integer[] genids){
        genreService.saveGameGenre(gid, genids);
        return Result.ok(null);
    }

    // import from steam json db
    @ApiOperation("import steam data")
    @PostMapping("import")
    public Result importSteamData(@RequestBody Map<String, SteamGame> steamGameMap){

        //TODO refactor: add this function to service
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (SteamGame steamGame : steamGameMap.values()) {
                    boolean is_succeed;
                    Game game = new Game();
                    game.setAppid(steamGame.getAppid());
                    game.setGamename(steamGame.getName());
                    game.setDeveloper(steamGame.getDeveloper());
                    game.setPublisher(steamGame.getPublisher());
                    game.setNegative(steamGame.getNegative());
                    game.setPositive(steamGame.getPositive());
                    game.setUserscore(steamGame.getUserscore());
                    game.setOwners(steamGame.getOwners());
                    game.setInitialprice(Integer.parseInt(steamGame.getInitialprice()!=null?steamGame.getInitialprice():"0"));
                    game.setCcu(steamGame.getCcu());

                    try{
                        is_succeed = gameService.save(game);
                    }catch (Exception e){
//                System.out.println(e.getMessage());
                        log.warn(e.getMessage());
                        // send to rabbit mq to add to elasticsearch db
                        Integer gameid = gameService.getIdByAppid(game.getAppid());
                        sendToAddMq(gameid);
                        continue;
                    }
                    if(is_succeed){
                        // send gameid to rabbitmq
                        count++;
                    }
                    // send to rabbit mq to add to elasticsearch db
                    Integer gameid = gameService.getIdByAppid(game.getAppid());
                    sendToAddMq(gameid);
                }
                log.debug("add total: " + count);
            }
        });


        return Result.ok(null);
    }

    //1 user list
    /**
     * pagination pathvariable
     * current page
     * limit item per page
     */
    @ApiOperation("pagination query")
    @GetMapping("{current}/{limit}")
    public Result  pageList(@PathVariable Integer current,
                            @PathVariable Integer limit,
                            GameQueryVo gameQueryVo) {

        //1 create page
        Page<Game> pageParam = new Page<>(current,limit);

        //2 service to search, return Page object

        IPage<Game> pageModel = gameService.selectGamePage(pageParam, gameQueryVo);

        return Result.ok(pageModel);
    }

    @GetMapping("{current}/{limit}/{low}/{high}")
    public Result  pageList(@PathVariable Integer current,
                            @PathVariable Integer limit,
                            @PathVariable Integer low,
                            @PathVariable Integer high,
                            GameQueryVo gameQueryVo) {

        //1 create page
        Page<Game> pageParam = new Page<>(current,limit);

        //2 service to search, return Page object

        IPage<Game> pageModel = gameService.selectGamePageWithScoreFilter(pageParam, gameQueryVo, low, high);

        return Result.ok(pageModel);
    }

    //2 select by id
    @ApiOperation("search by id")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Integer id){
        Game game = gameService.getById(id);
        return Result.ok(game);
    }
    //3 add user
    @ApiOperation("add game")
    @PostMapping("add")
    private Result add(@RequestBody Game game){
        boolean is_succeed =  gameService.save(game);
        if(is_succeed) {
            // get game id
            Integer gameid = gameService.getIdByAppid(game.getAppid());
            // send to add-mq
            sendToAddMq(gameid);

            return Result.ok(null);
        } else{
            return Result.fail(null);
        }
    }
    //4 update game
    @ApiOperation("update game")
    @PutMapping("update")
    public Result update(@RequestBody Game game){
        boolean is_succeed = gameService.updateById(game);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }
    //5 delete by id

    @ApiOperation("delete by id")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = gameService.removeById(id);
        if(is_succeed){
            //sent to remove
            sendToRemoveMq(id);

            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @PostMapping("getByIds")
    public Result getByids(@RequestBody GameIdsVo gameIdsVo){
        List<Game> res = new ArrayList<>();
        for (Integer id : gameIdsVo.getGids()) {
            Game game = gameService.getById(id);
            res.add(game);
        }
        return Result.ok(res);
    }

    //6 batched delete
    @ApiOperation("delete by Id list")
    @DeleteMapping("batchRemove")
    public Result deletes(@RequestBody List<Integer> idList){
        boolean is_succeed = gameService.removeByIds(idList);
        if(is_succeed){
            // send to remove mq
            for (Integer id : idList) {
                sendToRemoveMq(id);
            }
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @GetMapping("top/{n}")
    public Result topN(@PathVariable Integer n){
        LambdaQueryWrapper<Game> gameLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gameLambdaQueryWrapper.orderByDesc(Game::getUserscore);
        gameLambdaQueryWrapper.last("limit 0,"+n);
        List<Game> topNGame = gameService.list(gameLambdaQueryWrapper);
        return Result.ok(topNGame);
    }

    public void sendToAddMq(Integer game_id){
        rabbitService.sendMessage(MqConst.EXCHANGE_GAMES,
                MqConst.ROUTING_GAMES_ADD,
                game_id);
    }

    public void sendToRemoveMq(Integer game_id){
        rabbitService.sendMessage(MqConst.EXCHANGE_GAMES,
                MqConst.ROUTING_GAMES_REMOVE,
                game_id);
    }
}

