package org.wjy.gameforu.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wjy.gameforu.acl.service.GameService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.gameforu.Game;
import org.wjy.gameforu.vo.gameforu.GameQueryVo;

import java.util.List;

/**
 * Game Controller
 */
@Api(tags = "game management")
@RestController
@RequestMapping("/admin/acl/game")
@CrossOrigin
public class GameController {

    /**
     * inject service
     */
    @Autowired
    private GameService gameService;

    //1 user list
    /**
     * pagination pathvariable
     * current page
     * limit item per page
     */
    @ApiOperation("game list of pagination")
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

            return Result.ok(null);
        } else{
            return Result.fail(null);
        }
    }
    //4 update user
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
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //6 batched delete
    @ApiOperation("delete by Id list")
    @DeleteMapping("batchRemove")
    public Result deletes(@RequestBody List<Integer> idList){
        boolean is_succeed = gameService.removeByIds(idList);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }
}
