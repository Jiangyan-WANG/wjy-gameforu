package org.wjy.gameforu.admin2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.model.entity.Game;
import org.wjy.gameforu.admin2.mapper.GameMapper;
import org.wjy.gameforu.admin2.service.GameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.vo.GameQueryVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {
    @Override
    public IPage selectGamePage(Page<Game> pageParam, GameQueryVo gameQueryVo) {
        //1 get condition
        String gameName = gameQueryVo.getGameName().trim();

        //mp conditional object
        LambdaQueryWrapper<Game> wrapper = new LambdaQueryWrapper<>();
        //2 if condition not null, wrap search condition
        if(!StringUtils.isEmpty(gameName)){
            // method ref，condition. likely search
            // username like ?, gameName in sql
            wrapper.like(Game::getGamename, gameName);
        }
        // pagination
        IPage<Game> gamePage = baseMapper.selectPage(pageParam, wrapper);
        //3 else return all
        return gamePage ;
    }

    @Override
    public Integer getIdByAppid(Integer appid) {
        LambdaQueryWrapper<Game> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Game::getAppid,appid);
        Game game = baseMapper.selectOne(wrapper);
        return game.getId();
    }
}
