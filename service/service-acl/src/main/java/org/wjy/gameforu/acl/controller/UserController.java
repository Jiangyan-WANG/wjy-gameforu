package org.wjy.gameforu.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wjy.gameforu.acl.service.UserService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.gameforu.User;
import org.wjy.gameforu.vo.gameforu.GfuUserQueryVo;

import java.util.List;

/**
 * Role Controller
 */
@Api(tags = "user management")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class UserController {

    /**
     * inject service
     */
    @Autowired
    private UserService userService;

    //1 user list
    /**
     * pagination pathvariable
     * current page
     * limit item per page
     */
    @ApiOperation("user list of pagination")
    @GetMapping("{current}/{limit}")
    public Result  pageList(@PathVariable Integer current,
                            @PathVariable Integer limit,
                            GfuUserQueryVo gfuUserQueryVo) {

        //1 create page
        Page<User> pageParam = new Page<>(current,limit);

        //2 service to search, return Page object

        IPage<User> pageModel = userService.selectRolePage(pageParam, gfuUserQueryVo);

        return Result.ok(pageModel);
    }

    //2 select by id
    @ApiOperation("search by id")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Integer id){
        User gUser = userService.getById(id);
        return Result.ok(gUser);
    }
    //3 add user
    @ApiOperation("add user")
    @PostMapping("/add")
    private Result add(@RequestBody User user){
        boolean is_succeed =  userService.save(user);
        if(is_succeed) {

            return Result.ok(null);
        } else{
            return Result.fail(null);
        }
    }
    //4 update user
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
    //5 delete by id

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

    //6 batched delete
    @ApiOperation("delete by Id list")
    @DeleteMapping("batchRemove")
    public Result deletes(@RequestBody List<Integer> idList){
        boolean is_succeed = userService.removeByIds(idList);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }
}
