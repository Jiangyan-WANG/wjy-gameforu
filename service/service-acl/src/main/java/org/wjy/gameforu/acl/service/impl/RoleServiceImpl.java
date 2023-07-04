package org.wjy.gameforu.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.acl.mapper.RoleMapper;
import org.wjy.gameforu.acl.service.RoleService;
import org.wjy.gameforu.model.gameforu.GfuUser;
import org.wjy.gameforu.vo.gameforu.GfuUserQueryVo;

/**
 * service impl neet to add annotation
 * ServiceImpl injected RoleMapper
 * no need to inject use Autowired here again
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, GfuUser> implements RoleService {
    @Override
    public IPage selectRolePage(Page<GfuUser> pageParam, GfuUserQueryVo gfuUserQueryVo) {
        //1 get condition
        String userName = gfuUserQueryVo.getUserName();

        //mp conditional object
        LambdaQueryWrapper<GfuUser> wrapper = new LambdaQueryWrapper<>();
        //2 if condition not null, wrap search condition
        if(!StringUtils.isEmpty(userName)){
            // method ref，condition. likely search
            // username like ?, userName in sql
            wrapper.like(GfuUser::getUsername, userName);
        }
        // pagination
        IPage<GfuUser> gfuUserPage = baseMapper.selectPage(pageParam, wrapper);
        //3 else return all
        return gfuUserPage ;
    }
}
