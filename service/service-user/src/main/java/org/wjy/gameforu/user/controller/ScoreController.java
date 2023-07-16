package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Comment;
import org.wjy.gameforu.model.entity.Score;
import org.wjy.gameforu.user.service.CommentService;
import org.wjy.gameforu.user.service.ScoreService;
import org.wjy.gameforu.vo.GameCommentVo;
import org.wjy.gameforu.vo.GameScoreVo;

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
@RequestMapping("/score/user")
@Slf4j
@Api(tags = "score controller")
public class ScoreController {


    @Autowired
    ScoreService scoreService;

    @ApiOperation("add Score")
    @PostMapping("score/{uid}/{gid}")
    public Result addComment(@PathVariable Integer uid,
                             @PathVariable Integer gid,
                             @RequestBody GameScoreVo gameScoreVo){

        Score score = new Score();
        score.setUid(uid);
        score.setGid(gid);
        score.setScore(gameScoreVo.getScore());
        boolean is_succeed = scoreService.save(score);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("search score by gameid")
    @GetMapping("get/{gid}")
    public Result get(@PathVariable Integer gid) {
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("scoreTime");
        wrapper.eq("gid", gid);
        //TODO modify to pagination search
        List<Score> list = scoreService.list(wrapper);
        return Result.ok(list);
    }
}

