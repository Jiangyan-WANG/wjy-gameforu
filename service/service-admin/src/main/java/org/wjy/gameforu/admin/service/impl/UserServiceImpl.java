package org.wjy.gameforu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.admin.mapper.UserMapper;
import org.wjy.gameforu.admin.service.UserService;
import org.wjy.gameforu.model.gameforu.User;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

/**
 * service impl neet to add annotation
 * ServiceImpl injected RoleMapper
 * no need to inject use Autowired here again
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public IPage selectUserPage(Page<User> pageParam, UserQueryVo userQueryVo) {
        //1 get condition
        String userName = userQueryVo.getUserName();

        //mp conditional object
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //2 if condition not null, wrap search condition
        if(!StringUtils.isEmpty(userName)){
            // method ref，condition. likely search
            // username like ?, userName in sql
            wrapper.like(User::getUsername, userName);
        }
        // pagination
        IPage<User> userPage = baseMapper.selectPage(pageParam, wrapper);
        //3 else return all
        return userPage ;
    }
}
