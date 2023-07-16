package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Comment;
import org.wjy.gameforu.model.entity.ReplyComment;
import org.wjy.gameforu.user.service.CommentService;
import org.wjy.gameforu.user.service.ReplyCommentService;
import org.wjy.gameforu.vo.GameCommentVo;
import org.wjy.gameforu.vo.ReplyCommentVo;

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
@RequestMapping("/reply/user")
@Slf4j
@Api(tags = "reply comment controller")
public class ReplyCommentController {

    @Autowired
    ReplyCommentService replyCommentService;

    @ApiOperation("add reply")
    @PostMapping("reply/{uid}/{cid}")
    public Result addComment(@PathVariable Integer uid,
                             @PathVariable Integer cid,
                             @RequestBody ReplyCommentVo replyCommentVo){

        ReplyComment replyComment = new ReplyComment();
        replyComment.setUid(uid);
        replyComment.setCid(cid);
        replyComment.setContent(replyCommentVo.getReplyComment());
        boolean is_succeed = replyCommentService.save(replyComment);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("delete comment")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = replyCommentService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("search comment by comment id")
    @GetMapping("get/{cid}")
    public Result get(@PathVariable Integer cid) {
        QueryWrapper<ReplyComment> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("commentTime");
        wrapper.eq("cid", cid);
        //TODO modify to pagination search
        List<ReplyComment> list = replyCommentService.list(wrapper);
        return Result.ok(list);
    }
}

