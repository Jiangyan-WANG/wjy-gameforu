package org.wjy.gameforu.admin2.service;

import org.wjy.gameforu.model.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
public interface UserRoleService extends IService<UserRole> {

    List<UserRole> getUserRoleByUserId(Integer id);
}
