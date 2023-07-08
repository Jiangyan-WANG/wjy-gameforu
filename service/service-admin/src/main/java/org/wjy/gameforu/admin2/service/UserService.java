package org.wjy.gameforu.admin2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wjy.gameforu.admin2.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

import java.util.List;
import java.util.Map;

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

    /**
     * get roles of the user by user id
     * @param id user id
     * @return
     */
    Map<String, Object> getRolesByUserId(Integer id);

    Boolean setRoles(Integer id, List<Integer> roleIds);
}
