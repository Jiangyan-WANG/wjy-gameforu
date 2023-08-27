package org.wjy.gameforu.admin2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.admin2.mapper.ProposedGameMapper;
import org.wjy.gameforu.admin2.mapper.RoleMapper;
import org.wjy.gameforu.admin2.service.ProposedGameService;
import org.wjy.gameforu.admin2.service.RoleService;
import org.wjy.gameforu.model.entity.ProposedGame;
import org.wjy.gameforu.model.entity.Role;
import org.wjy.gameforu.vo.RoleQueryVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class ProposedGameServiceImpl extends ServiceImpl<ProposedGameMapper, ProposedGame> implements ProposedGameService {

}
