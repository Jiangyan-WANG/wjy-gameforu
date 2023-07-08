package org.wjy.gameforu.admin2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.wjy.gameforu.admin2.entity.UserRole;
import org.wjy.gameforu.admin2.mapper.UserRoleMapper;
import org.wjy.gameforu.admin2.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<UserRole> getUserRoleByUserId(Integer id) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUid, id);
        wrapper.orderByAsc(UserRole::getId);
        List<UserRole> userRoleList = baseMapper.selectList(wrapper);
        return userRoleList;
    }
}
