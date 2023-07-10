package org.wjy.gameforu.admin2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.model.entity.Role;
import org.wjy.gameforu.admin2.service.RoleService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.vo.RoleQueryVo;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Api(tags = "role management")
@RestController
@RequestMapping("/role/admin")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //1 pagenation query
    @ApiOperation("pagination query")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Integer current,
                           @PathVariable Integer limit,
                           RoleQueryVo roleQueryVo) {
        //1 create page
        Page<Role> pageParam = new Page<>(current,limit);

        //2 service to search, return Page object

        IPage<Role> pageModel = roleService.selectRolePage(pageParam, roleQueryVo);

        return Result.ok(pageModel);
    }

    //2 add role
    @ApiOperation("add role")
    @PostMapping("add")
    public Result add(@RequestBody Role role){
        Boolean is_succeed = roleService.save(role);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //3 delete role by id
    @ApiOperation("delete role")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = roleService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //4 delete rold by ids
    @ApiOperation("batch delete")
    @DeleteMapping("batchRemove")
    public Result deletes(@RequestBody List<Integer> ids){
        boolean is_succeed = roleService.removeByIds(ids);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //5 update role
    @ApiOperation("update role")
    @PutMapping("update")
    public Result update(@RequestBody Role role){
        boolean is_succeed = roleService.updateById(role);
        if(is_succeed) {
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //6 query role by id
    @ApiOperation("query role by id")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Integer id){
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

}

