package org.wjy.gameforu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.wjy.gameforu.admin2.mapper.GenreMapper;
import org.wjy.gameforu.admin2.service.GenreService;
import org.wjy.gameforu.model.entity.GameGenre;
import org.wjy.gameforu.model.entity.Genre;
import org.wjy.gameforu.model.entity.Prefer;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.user.mapper.PreferMapper;
import org.wjy.gameforu.user.service.PreferService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class PreferServiceImpl extends ServiceImpl<PreferMapper, Prefer> implements PreferService {

    @Resource
    private GenreMapper genreMapper;

    @Autowired
    GenreService genreService;

    @Override
    public Boolean setPrefer(Integer uid, Integer[] preferIds) {
        // delete old preferss
        LambdaQueryWrapper<Prefer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Prefer::getUid, uid);
        baseMapper.delete(wrapper);
        // add new prefers
        if(preferIds==null) return true;
        List<Prefer> preferList = new ArrayList<>();

        for (Integer preferId : preferIds) {
            Prefer prefer = new Prefer();
            prefer.setUid(uid);
            prefer.setGenid(preferId);
            preferList.add(prefer);
        }
        boolean is_succeed = this.saveBatch(preferList);
        return is_succeed;
    }

    @Override
    public Map<String, Object> getPreferByUid(Integer uid) {
        //1 get all genres
        QueryWrapper<Genre> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        List<Genre> allGenreList = genreMapper.selectList(queryWrapper);
        Map<String, Object> res = new HashMap<>();
        //2 get genres of specific game id
        //2.1 get GameGenre List of gid -> List<GameGenre>
        LambdaQueryWrapper<Prefer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Prefer::getUid, uid).orderByAsc(Prefer::getPreferTime);
        List<Prefer> preferGenreList = this.list(wrapper);

        //2.2 get genid List by GameGenre List -> List<Integer>
        List<Integer> genreIdList = preferGenreList.stream()
                .map(item -> item.getGenid())
                .collect(Collectors.toList());

        //2.3 get genre List of genid List -> List<Genre>
        List<Genre> currentGenreList = new ArrayList<>();
        for (Genre genre: allGenreList) {
            if(genreIdList.contains(genre.getId())){
                currentGenreList.add(genre);
            }
        }

        //all genres
        res.put("allGenreList", allGenreList);

        //current genres of specific game
        res.put("currentGenreList", currentGenreList);
        return res;
    }
}
