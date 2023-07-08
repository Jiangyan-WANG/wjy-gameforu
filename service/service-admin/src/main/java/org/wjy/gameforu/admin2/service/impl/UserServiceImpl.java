package org.wjy.gameforu.admin2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.admin2.entity.Role;
import org.wjy.gameforu.admin2.entity.User;
import org.wjy.gameforu.admin2.entity.UserRole;
import org.wjy.gameforu.admin2.mapper.UserMapper;
import org.wjy.gameforu.admin2.service.RoleService;
import org.wjy.gameforu.admin2.service.UserRoleService;
import org.wjy.gameforu.admin2.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public IPage<User> selectUserPage(Page<User> pageParam, UserQueryVo userQueryVo) {

        //1 query param
        String userName = userQueryVo.getUserName();

        //2 wrapper encapsulation
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        if(!StringUtils.isEmpty(userName)){
            wrapper.like(User::getUsername, userName);
        }
        //3 order
        wrapper.orderByAsc(User::getId);
        //4 query and pagination
        IPage<User> res = baseMapper.selectPage(pageParam, wrapper);
        return res;
    }

    @Override
    public Map<String, Object> getRolesByUserId(Integer id) {
        Map<String, Object> res = new HashMap<>();
        //1 get all List<Roles>
        List<Role> allRoleList = roleService.selectList();

        res.put("allRoleList", allRoleList);

        //2 get List<UserRole> of the user by user id, then get List<id> of the Role
        List<UserRole> userRoleList = userRoleService.getUserRoleByUserId(id);

        List<Integer> roleIdListOfUser = userRoleList.stream()
                .map(item->item.getRid())
                .collect(Collectors.toList());

        //3 get List<Role> by List<ids>(Role)
        List<Role> roleListOfUser = new ArrayList<>();
        for (Role role : allRoleList) {
            if(roleIdListOfUser.contains(role.getId())){
                roleListOfUser.add(role);
            }
        }
        res.put("roleListOfUser", roleListOfUser);
        return res;
    }

    @Override
    public Boolean setRoles(Integer id, List<Integer> roleIds) {
        List<UserRole> userRoleList = new ArrayList<>();
        for (Integer roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUid(id);
            userRole.setRid(roleId);
            userRoleList.add(userRole);
        }
        boolean is_succeed = userRoleService.saveBatch(userRoleList);
        return is_succeed;
    }


}
