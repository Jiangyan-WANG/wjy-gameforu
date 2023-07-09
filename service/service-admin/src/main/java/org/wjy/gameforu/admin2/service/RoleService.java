package org.wjy.gameforu.admin2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wjy.gameforu.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.vo.RoleQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
public interface RoleService extends IService<Role> {

    /**
     * pagination query of the role
     */
    IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    List<Role> selectList();
}
