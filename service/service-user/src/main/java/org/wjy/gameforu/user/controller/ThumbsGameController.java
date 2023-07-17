package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public Result addComment(@PathVariable Integer uid,
                             @PathVariable Integer gid,
                             @RequestBody ThumbsVo thumbsVo){

        ThumbsGame thumbsGame = new ThumbsGame();
        thumbsGame.setUid(uid);
        thumbsGame.setGid(gid);
        thumbsGame.setUp(thumbsVo.getThumbs()==1);
        boolean is_succeed = thumbsGameService.save(thumbsGame);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
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
}

