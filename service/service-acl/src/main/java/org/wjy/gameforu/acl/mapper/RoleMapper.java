package org.wjy.gameforu.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.wjy.gameforu.model.gameforu.GfuUser;

@Repository
public interface RoleMapper extends BaseMapper<GfuUser> {
}
