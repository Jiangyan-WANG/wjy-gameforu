package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.admin2.service.GameService;
import org.wjy.gameforu.admin2.service.UserService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Comment;
import org.wjy.gameforu.model.entity.User;
import org.wjy.gameforu.user.service.CommentService;
import org.wjy.gameforu.vo.GameCommentVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/comment/user")
@Slf4j
@Api(tags = "game comment api")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;

    @ApiOperation("add comment")
    @PostMapping("comment/{uid}/{gid}")
    public Result addComment(@PathVariable Integer uid,
                             @PathVariable Integer gid,
                             @RequestBody GameCommentVo gameCommentVo){

        Comment comment = new Comment();
        comment.setUid(uid);
        comment.setGid(gid);
        comment.setContent(gameCommentVo.getGameComment());
        boolean is_succeed = commentService.save(comment);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("delete comment")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = commentService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("search comment by gameid")
    @GetMapping("get/{gid}")
    public Result get(@PathVariable Integer gid) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("comment_time");
        wrapper.eq("gid", gid);
        //TODO modify to pagination search
        List<Comment> commentList = commentService.list(wrapper);
        List<User> userList = new ArrayList<>();
        for (Comment comment : commentList) {
            User user = userService.getById(comment.getUid());
            userList.add(user);
        }
        // res map
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("commentList", commentList);
        resMap.put("userList",userList);
        return Result.ok(resMap);
    }
}

