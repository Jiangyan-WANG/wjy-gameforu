package org.wjy.gameforu.admin2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wjy.gameforu.admin2.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
public interface UserService extends IService<User> {

    IPage<User> selectUserPage(Page<User> pageParam, UserQueryVo userQueryVo);
}
