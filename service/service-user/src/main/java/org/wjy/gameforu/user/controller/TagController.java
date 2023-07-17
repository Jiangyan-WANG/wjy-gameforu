package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Comment;
import org.wjy.gameforu.model.entity.Tag;
import org.wjy.gameforu.user.service.CommentService;
import org.wjy.gameforu.user.service.TagService;
import org.wjy.gameforu.vo.GameCommentVo;
import org.wjy.gameforu.vo.GameTagVo;

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
@RequestMapping("/tag/user")
@Slf4j
@Api(tags="tag controller")
public class TagController {


    @Autowired
    TagService tagService;

    @ApiOperation("add tag")
    @PostMapping("tag/{uid}/{gid}")
    public Result addComment(@PathVariable Integer uid,
                             @PathVariable Integer gid,
                             @RequestBody GameTagVo gameTagVo){

        Tag tag = new Tag();
        tag.setUid(uid);
        tag.setGid(gid);
        tag.setTagname(gameTagVo.getGameTag());
        boolean is_succeed = tagService.save(tag);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("delete tag")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = tagService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("search comment by gameid")
    @GetMapping("get/{gid}")
    public Result get(@PathVariable Integer gid) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("commentTime");
        wrapper.eq("gid", gid);
        //TODO modify to pagination search
        List<Tag> list = tagService.list(wrapper);
        return Result.ok(list);
    }
}

