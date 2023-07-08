package org.wjy.gameforu.admin2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.admin2.entity.User;
import org.wjy.gameforu.admin2.mapper.UserMapper;
import org.wjy.gameforu.admin2.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

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
}
