package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Tag;
import org.wjy.gameforu.model.entity.ThumbsComment;
import org.wjy.gameforu.user.service.TagService;
import org.wjy.gameforu.user.service.ThumbsCommentService;
import org.wjy.gameforu.vo.GameTagVo;
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
@RequestMapping("/thumbscomment/user")
@Slf4j
@Api(tags = "thumbs comment controller")
public class ThumbsCommentController {


    @Autowired
    ThumbsCommentService thumbsCommentService;

    @ApiOperation("add thumbs")
    @PostMapping("thumbs/{uid}/{cid}")
    public Result thumbs(@PathVariable Integer uid,
                             @PathVariable Integer cid,
                             @RequestBody ThumbsVo thumbsVo){

        ThumbsComment thumbsComment = new ThumbsComment();
        thumbsComment.setUid(uid);
        thumbsComment.setCid(cid);
        thumbsComment.setUp(thumbsVo.getThumbs()==1);

        // check if thumbs comment exist
        LambdaQueryWrapper<ThumbsComment> thumbsCommentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        thumbsCommentLambdaQueryWrapper.eq(ThumbsComment::getUid,uid).eq(ThumbsComment::getCid,cid);
        ThumbsComment existedThumbsComment = thumbsCommentService.getOne(thumbsCommentLambdaQueryWrapper);
        if(existedThumbsComment!=null){
            if(thumbsComment.getUp()==existedThumbsComment.getUp()){
                thumbsCommentService.removeById(existedThumbsComment.getId());
                return Result.ok(0);
            } else{
                thumbsCommentService.removeById(existedThumbsComment.getId());
                thumbsCommentService.save(thumbsComment);
                return Result.ok(2);
            }
        }
        else{
            boolean is_success = thumbsCommentService.save(thumbsComment);
            if(is_success){
                return Result.ok(1);
            }
            else{
                return Result.fail(-1);
            }
        }
    }

    @ApiOperation("delete tag")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = thumbsCommentService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("search thumbs by comment id")
    @GetMapping("get/{cid}")
    public Result get(@PathVariable Integer cid) {
        QueryWrapper<ThumbsComment> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("thumbsTime");
        wrapper.eq("cid", cid);
        //TODO modify to pagination search
        List<ThumbsComment> list = thumbsCommentService.list(wrapper);
        return Result.ok(list);
    }

    @ApiOperation("get thumb comment info")
    @GetMapping("get/{uid}/{cid}")
    public Result getInfo(@PathVariable Integer uid,
                      @PathVariable Integer cid){
        LambdaQueryWrapper<ThumbsComment> thumbsCommentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        thumbsCommentLambdaQueryWrapper.eq(ThumbsComment::getUid,uid).eq(ThumbsComment::getCid,cid);
        ThumbsComment existedThumbComment = thumbsCommentService.getOne(thumbsCommentLambdaQueryWrapper);
        if(existedThumbComment!=null){
            if(existedThumbComment.getUp()){
                return Result.ok(1);
            } else{
                return Result.ok(0);
            }
        }else{
            return Result.ok(-1);
        }
    }
}

