package org.wjy.gameforu.admin2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.admin2.entity.Game;
import org.wjy.gameforu.admin2.entity.Role;
import org.wjy.gameforu.admin2.mapper.RoleMapper;
import org.wjy.gameforu.admin2.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.vo.gameforu.RoleQueryVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //1 get searched role name
        String roleName = roleQueryVo.getRoleName();
        //2 wrapper
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like("rolename", roleName);
        }
        wrapper.orderByAsc("id");
        //3 Ipage
        IPage<Role> res = baseMapper.selectPage(pageParam, wrapper);
        return res;
    }

    @Override
    public List<Role> selectList() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<Role> roles = baseMapper.selectList(wrapper);
        return roles;
    }
}
