package org.wjy.gameforu.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wjy.gameforu.acl.service.RoleService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.gameforu.GfuUser;
import org.wjy.gameforu.vo.gameforu.GfuUserQueryVo;

/**
 * Role Controller
 */
@Api(tags = "用户角色管理")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {

    /**
     * inject service
     */
    @Autowired
    private RoleService roleService;

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
        Page<GfuUser> pageParam = new Page<>(current,limit);

        //2 service to search, return Page object

        IPage<GfuUser> pageModel = roleService.selectRolePage(pageParam, gfuUserQueryVo);

        return Result.ok(pageModel);
    }

    //2 select by id
    //3 add user
    //4 update user
    //5 delete by id
    //6 batched delete
}
