package org.wjy.gameforu.admin2.service.impl;

import org.wjy.gameforu.admin2.entity.User;
import org.wjy.gameforu.admin2.mapper.UserMapper;
import org.wjy.gameforu.admin2.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
