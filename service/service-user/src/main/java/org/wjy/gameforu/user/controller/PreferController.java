package org.wjy.gameforu.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.user.service.PreferService;
import org.wjy.gameforu.vo.SetPreferVo;

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
@Slf4j
@Api(tags = "prefer set")
public class PreferController {

    @Autowired
    PreferService preferService;

    @ApiOperation("get prefer")
    @GetMapping("prefer/{uid}")
    public Result getPrefer(@PathVariable Integer uid){
        Map<String,Object> resMap = preferService.getPreferByUid(uid);
        return Result.ok(resMap);
    }

    @ApiOperation("set prefer")
    @PostMapping("setprefer/{uid}")
    public Result setPrefer(@PathVariable Integer uid,
            @RequestBody(required = false) SetPreferVo setPreferVo){
        Boolean is_succeed = preferService.setPrefer(uid, setPreferVo.getPreferIdsArray());
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

}

