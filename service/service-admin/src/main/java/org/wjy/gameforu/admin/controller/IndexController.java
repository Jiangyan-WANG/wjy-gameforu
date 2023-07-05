package org.wjy.gameforu.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wjy.gameforu.common.result.Result;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "admin access control list")
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {
    /**
     * admin controller
     */

    /**
     * login
     */
    @ApiOperation("login")
    @PostMapping("login")
     public Result login(){
        // return token
        Map<String, String> map = new HashMap<>();
        map.put("token","token-admin");
        return Result.ok(map);
    }

    /**
     * get info of users
     */
    @ApiOperation("user info")
    @GetMapping("info")
    public Result info(){
        // return name and avatar
        Map<String, String> map = new HashMap<>();
        map.put("name","admin");
        map.put("avatar","jinitaimei");
        return Result.ok(map);
    }

    /**
     * logout
     */
    @ApiOperation("logout")
    @PostMapping("logout")
    public Result logout(){

        return Result.ok(null);
    }

}
