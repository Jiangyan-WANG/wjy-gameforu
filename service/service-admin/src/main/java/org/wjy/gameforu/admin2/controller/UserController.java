package org.wjy.gameforu.admin2.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.admin2.entity.User;
import org.wjy.gameforu.admin2.service.UserService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Api(tags = "user management")
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    //1 pagination query
    @ApiOperation("pagination query")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Integer current,
                           @PathVariable Integer limit,
                           UserQueryVo userQueryVo){
        Page<User> pageParam = new Page<>(current,limit);
        IPage<User> pageModel = userService.selectUserPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }

    //2 add user
    @ApiOperation("add user")
    @PostMapping("add")
    public Result add(@RequestBody User user){
        boolean is_succeed = userService.save(user);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }


    //3 delete user by id
    @ApiOperation("delete by id")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = userService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //4 batch delete user by ids
    @ApiOperation("batch delete")
    @DeleteMapping("batchRemove")
    public Result deletes(@RequestBody List<Integer> ids){
        boolean is_succeed = userService.removeByIds(ids);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //5 update user
    @ApiOperation("update user")
    @PutMapping("update")
    public Result update(@RequestBody User user){
        boolean is_succeed = userService.updateById(user);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //6 query user by id
    @ApiOperation("query by id")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.ok(user);
    }
}

