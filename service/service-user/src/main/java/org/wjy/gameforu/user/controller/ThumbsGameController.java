package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.netty.handler.codec.marshalling.ThreadLocalUnmarshallerProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.ThumbsComment;
import org.wjy.gameforu.model.entity.ThumbsGame;
import org.wjy.gameforu.user.service.ThumbsCommentService;
import org.wjy.gameforu.user.service.ThumbsGameService;
import org.wjy.gameforu.vo.ThumbsVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/thumbsgame/user")
@Slf4j
@Api(tags = "thumbs game controller")
public class ThumbsGameController {

    @Autowired
    ThumbsGameService thumbsGameService;

    @ApiOperation("add thumbs")
    @PostMapping("thumbs/{uid}/{gid}")
    public Result thumbs(@PathVariable Integer uid,
                             @PathVariable Integer gid,
                             @RequestBody ThumbsVo thumbsVo){

        ThumbsGame thumbsGame = new ThumbsGame();
        thumbsGame.setUid(uid);
        thumbsGame.setGid(gid);
        thumbsGame.setUp(thumbsVo.getThumbs()==1);
        // judge if the records exists
        LambdaQueryWrapper<ThumbsGame> thumbsGameLambdaQueryWrapper = new LambdaQueryWrapper<>();
        thumbsGameLambdaQueryWrapper.eq(ThumbsGame::getUid,thumbsGame.getUid())
                .eq(ThumbsGame::getGid,thumbsGame.getGid());
        ThumbsGame existedThumbs = thumbsGameService.getOne(thumbsGameLambdaQueryWrapper);
        if(existedThumbs!=null){
            if(existedThumbs.getUp()==thumbsGame.getUp()){
                //
                thumbsGameService.removeById(existedThumbs.getId());
                return Result.ok(0);
            }else{
                thumbsGameService.removeById(existedThumbs.getId());
                thumbsGameService.save(thumbsGame);
                return Result.ok(2);
            }
        }else{
            boolean is_succeed = thumbsGameService.save(thumbsGame);
            if(is_succeed){
                return Result.ok(1);
            }else{
                return Result.fail(-1);
            }
        }

    }

    @ApiOperation("user id list who like game")
    @GetMapping("/likegameuserids/{gid}")
    public Result likeGameUser(@PathVariable Integer gid){
        LambdaQueryWrapper<ThumbsGame> tgLqw = new LambdaQueryWrapper<>();
        tgLqw.eq(ThumbsGame::getGid,gid);
        List<ThumbsGame> thumbsGameList = thumbsGameService.list(tgLqw);
        List<Integer> likeGameUserIdList = thumbsGameList.stream().map(thumbsGame -> {
            if(thumbsGame.getUp()==true){
                return thumbsGame.getUid();
            } else {
                return null;
            }
        }).collect(Collectors.toList());
        return Result.ok(likeGameUserIdList.toArray());
    }

    @ApiOperation("user id list who like game")
    @GetMapping("/unlikegameuserids/{gid}")
    public Result unLikeGameUser(@PathVariable Integer gid){
        LambdaQueryWrapper<ThumbsGame> tgLqw = new LambdaQueryWrapper<>();
        tgLqw.eq(ThumbsGame::getGid,gid);
        List<ThumbsGame> thumbsGameList = thumbsGameService.list(tgLqw);
        List<Integer> likeGameUserIdList = thumbsGameList.stream().map(thumbsGame -> {
            if(thumbsGame.getUp()==false){
                return thumbsGame.getUid();
            } else {
                return null;
            }
        }).collect(Collectors.toList());
        return Result.ok(likeGameUserIdList.toArray());
    }

    @ApiOperation("delete tag")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = thumbsGameService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("search thumbs by comment id")
    @GetMapping("get/{gid}")
    public Result get(@PathVariable Integer gid) {
        QueryWrapper<ThumbsGame> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("thumbsTime");
        wrapper.eq("gid", gid);
        //TODO modify to pagination search
        List<ThumbsGame> list = thumbsGameService.list(wrapper);
        return Result.ok(list);
    }

    @ApiOperation("thumb game info")
    @GetMapping("get/{uid}/{gid}")
    public Result getInfo(@PathVariable Integer uid,
                          @PathVariable Integer gid){
        LambdaQueryWrapper<ThumbsGame> thumbsGameLambdaQueryWrapper = new LambdaQueryWrapper<>();
        thumbsGameLambdaQueryWrapper.eq(ThumbsGame::getUid,uid).eq(ThumbsGame::getGid,gid);
        ThumbsGame info = thumbsGameService.getOne(thumbsGameLambdaQueryWrapper);
        if(info==null){
            return Result.ok(-1);
        }else{
            int flag = info.getUp()?1:0;
            return Result.ok(flag);
        }
    }
}

