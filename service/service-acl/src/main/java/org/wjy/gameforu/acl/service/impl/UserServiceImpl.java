package org.wjy.gameforu.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.acl.mapper.UserMapper;
import org.wjy.gameforu.acl.service.UserService;
import org.wjy.gameforu.model.gameforu.User;
import org.wjy.gameforu.vo.gameforu.GfuUserQueryVo;

/**
 * service impl neet to add annotation
 * ServiceImpl injected RoleMapper
 * no need to inject use Autowired here again
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public IPage selectRolePage(Page<User> pageParam, GfuUserQueryVo gfuUserQueryVo) {
        //1 get condition
        String userName = gfuUserQueryVo.getUserName();

        //mp conditional object
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //2 if condition not null, wrap search condition
        if(!StringUtils.isEmpty(userName)){
            // method ref，condition. likely search
            // username like ?, userName in sql
            wrapper.like(User::getUsername, userName);
        }
        // pagination
        IPage<User> gfuUserPage = baseMapper.selectPage(pageParam, wrapper);
        //3 else return all
        return gfuUserPage ;
    }
}
