package org.wjy.gameforu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.wjy.gameforu.admin2.service.UserService;
import org.wjy.gameforu.common.auth.AuthContextHolder;
import org.wjy.gameforu.common.constant.RedisConst;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.User;
import org.wjy.gameforu.model.entity.UserInfo;
import org.wjy.gameforu.vo.LoginDataVo;
import org.wjy.gameforu.vo.UserInfoResponseVo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  permission controller
 * </p>
 *
 * @author J Wang
 * @since 2023-07-09
 */
@RestController
@RequestMapping("/user/permission")
@Slf4j
@Api(tags = "user permission control")
public class PermissionController {
    @Autowired
    UserService userService;

    @Resource
    RedisTemplate redisTemplate;

    @ApiOperation("login")
    @PostMapping("login")
    public Result login(@RequestBody LoginDataVo loginDataVo){
        Map<String, Object> res = userService.loginCheck(loginDataVo);
        if(res.get("pass").equals("yes")){
            User user = (User) res.get("user");
            String token = (String) res.get("token");

            // contruct user info data
            UserInfoResponseVo userInfoResponseVo = new UserInfoResponseVo();
            userInfoResponseVo.setUsername(user.getUsername());
            // set avatar: example John Smith--->>>JS
            String[] avatarContructor = user.getUsername().trim().split(" ");
            String avatar ="";
            for (String s : avatarContructor) {
                avatar+=s.toUpperCase().charAt(0);
            }
            userInfoResponseVo.setAvatar(avatar);
            // save user info to redis
            redisTemplate.opsForValue().set(RedisConst.USER_LOGIN_KEY_PREFIX+user.getId(),
                    userInfoResponseVo);

            // construct return data
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            userInfo.setPhonenumber(user.getPhonenumber());
            userInfo.setEmail(user.getEmail());
            userInfo.setCreateTime(user.getCreateTime());
            userInfo.setToken(token);

            return Result.ok(userInfo);
        }else{
            return Result.fail(null);
        }
    }

    @ApiOperation("logout")
    @PostMapping("logout")
    public Result logout(){
        return Result.ok(null);
    }

    @ApiOperation("info")
    @GetMapping("info")
    public Result info(){
        UserInfoResponseVo userInfoResponseVo = new UserInfoResponseVo();
        userInfoResponseVo.setUsername(AuthContextHolder.getUsername());
        userInfoResponseVo.setAvatar(AuthContextHolder.getAvatar());

        return Result.ok(userInfoResponseVo);
    }
}
