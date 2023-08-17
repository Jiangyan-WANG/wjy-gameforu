package org.wjy.gameforu.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.admin2.service.UserService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Follower;
import org.wjy.gameforu.model.entity.User;
import org.wjy.gameforu.user.service.FollowerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/follower/user")
@Slf4j
@Api(tags = "follower management")
public class FollowerController {


    @Autowired
    private FollowerService followerService;

    @Autowired
    private UserService userService;

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

        LambdaQueryWrapper<Follower> followerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        followerLambdaQueryWrapper.eq(Follower::getUid, uid).eq(Follower::getFuid, fuid);
        Follower existedFollower = followerService.getOne(followerLambdaQueryWrapper);
        if(existedFollower!=null){
            followerService.removeById(existedFollower.getId());
            return Result.ok(0);
        }else{
            Boolean is_succeed = followerService.save(follower);
            if(is_succeed){
                return Result.ok(1);
            }else{
                return Result.fail(-1);
            }

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

    @GetMapping("get/{uid}/{fuid}")
    public Result getInfo(@PathVariable Integer uid,
                      @PathVariable Integer fuid){
        LambdaQueryWrapper<Follower> followerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        followerLambdaQueryWrapper.eq(Follower::getUid,uid).eq(Follower::getFuid,fuid);
        Follower existedFollower = followerService.getOne(followerLambdaQueryWrapper);
        if(existedFollower!=null){
            return Result.ok(1);
        }
        return Result.ok(0);
    }

    @GetMapping("myfollows/{fuid}")
    public Result getMyFollows(@PathVariable Integer fuid){
        LambdaQueryWrapper<Follower> followerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        followerLambdaQueryWrapper.eq(Follower::getFuid,fuid);
        followerLambdaQueryWrapper.orderByDesc(Follower::getFollowTime);
        List<Follower> myFollows = followerService.list(followerLambdaQueryWrapper);
        List<Integer> myFollowsIds = myFollows.stream().map(follower -> follower.getUid()).collect(Collectors.toList());
        List<User> myFollowsUser = new ArrayList<>();
        for (Integer myFollowsId : myFollowsIds) {
            myFollowsUser.add(userService.getById(myFollowsId));
        }
        return Result.ok(myFollowsUser);
    }

    @GetMapping("myfollower/{uid}")
    public Result getMyFollower(@PathVariable Integer uid){
        LambdaQueryWrapper<Follower> followerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        followerLambdaQueryWrapper.eq(Follower::getUid,uid);
        followerLambdaQueryWrapper.orderByDesc(Follower::getFollowTime);
        List<Follower> myFollower = followerService.list(followerLambdaQueryWrapper);
        List<Integer> myFollowerIds = myFollower.stream().map(follower -> follower.getFuid()).collect(Collectors.toList());
        List<User> myFollowerUser = new ArrayList<>();
        for (Integer myFollowerId : myFollowerIds) {
            myFollowerUser.add(userService.getById(myFollowerId));
        }
        return Result.ok(myFollowerUser);
    }

}

