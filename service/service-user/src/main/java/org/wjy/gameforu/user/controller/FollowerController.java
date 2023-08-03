package org.wjy.gameforu.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Follower;
import org.wjy.gameforu.user.service.FollowerService;

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
@RequestMapping("/follower/user")
@Slf4j
@Api(tags = "follower management")
public class FollowerController {


    @Autowired
    private FollowerService followerService;

    /**
     *
     * @param uid user id
     * @param fuid the follower user id
     * @return
     */
    @ApiOperation("follow")
    @PostMapping("follow/{uid}/{fuid}")
    public Result follow(@PathVariable Integer uid,
                         @PathVariable Integer fuid){
        Follower follower = new Follower();
        follower.setUid(uid);
        follower.setFuid(fuid);
        Boolean is_succeed = followerService.save(follower);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @DeleteMapping("unfollow/{id}")
    public Result unfollow(@PathVariable Integer id){
        boolean is_succeed = followerService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    @GetMapping("follow/dynamic/{uid}")
    public Result dynamic(@PathVariable Integer uid){
        Map<String, Object> resmap = followerService.getDynamicsByUid(uid);
        return Result.ok(resmap);
    }
}

