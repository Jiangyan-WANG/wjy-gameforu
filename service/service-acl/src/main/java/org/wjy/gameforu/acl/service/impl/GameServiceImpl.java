package org.wjy.gameforu.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.acl.mapper.GameMapper;
import org.wjy.gameforu.acl.mapper.UserMapper;
import org.wjy.gameforu.acl.service.GameService;
import org.wjy.gameforu.acl.service.UserService;
import org.wjy.gameforu.model.gameforu.Game;
import org.wjy.gameforu.model.gameforu.User;
import org.wjy.gameforu.vo.gameforu.GameQueryVo;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

/**
 * service impl neet to add annotation
 * ServiceImpl injected RoleMapper
 * no need to inject use Autowired here again
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {
    @Override
    public IPage selectGamePage(Page<Game> pageParam, GameQueryVo gameQueryVo) {
        //1 get condition
        String gameName = gameQueryVo.getGameName();

        //mp conditional object
        LambdaQueryWrapper<Game> wrapper = new LambdaQueryWrapper<>();
        //2 if condition not null, wrap search condition
        if(!StringUtils.isEmpty(gameName)){
            // method ref，condition. likely search
            // username like ?, gameName in sql
            wrapper.like(Game::getName, gameName);
        }
        // pagination
        IPage<Game> gamePage = baseMapper.selectPage(pageParam, wrapper);
        //3 else return all
        return gamePage ;
    }
}
