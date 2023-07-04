package org.wjy.gameforu.acl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wjy.gameforu.common.result.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {
    /**
     * admin controller
     */

    /**
     * login
     */
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
    @PostMapping("logout")
    public Result logout(){

        return Result.ok(null);
    }

}
