package org.wjy.gameforu.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.model.gameforu.User;
import org.wjy.gameforu.vo.gameforu.GfuUserQueryVo;

/**
 * RoleService need to extend the Iservice interface of mp
 * entity class User is set
 */
public interface UserService extends IService<User> {
    /**
     * conditional search
     * @param pageParam
     * @param gfuUserQueryVo
     * @return
     */
    IPage selectRolePage(Page<User> pageParam, GfuUserQueryVo gfuUserQueryVo);
}