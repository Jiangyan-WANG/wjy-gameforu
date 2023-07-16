package org.wjy.gameforu.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.user.service.PreferService;

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
@RequestMapping("/prefer/user")
public class PreferController {

    @Autowired
    PreferService preferService;

    @GetMapping("prefer/{uid}")
    public Result getPrefer(@PathVariable Integer uid){
        Map<String,Object> resMap = preferService.getPreferByUid(uid);
        return Result.ok(resMap);
    }

    @PostMapping("setprefer")
    public Result setPrefer(@RequestParam Integer uid,
                            @RequestParam(required = false) Integer[] preferIds){
        Boolean is_succeed = preferService.setPrefer(uid, preferIds);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }
}

