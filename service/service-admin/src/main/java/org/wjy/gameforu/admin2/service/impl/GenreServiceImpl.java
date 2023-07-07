package org.wjy.gameforu.admin2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.admin2.entity.Genre;
import org.wjy.gameforu.admin2.mapper.GenreMapper;
import org.wjy.gameforu.admin2.service.GameGenreService;
import org.wjy.gameforu.admin2.service.GenreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.admin2.entity.GameGenre;
import org.wjy.gameforu.vo.gameforu.GenreQueryVo;

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
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre> implements GenreService {

    @Autowired
    private GameGenreService gameGenreService;

    @Override
    public void saveGameGenre(Integer gid, Integer[] genids) {
        //1 delete previous genres
        LambdaQueryWrapper<GameGenre> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GameGenre::getGid,gid);
        gameGenreService.remove(wrapper);

        //2 set new genres

        List<GameGenre> gameGenreList = new ArrayList<>();
        for (Integer genid: genids) {
            GameGenre gameGenre = new GameGenre();
            gameGenre.setGenid(genid);
            gameGenre.setGid(gid);
            gameGenreList.add(gameGenre);
//            gameGenreService.save(gameGenre);
        }
        gameGenreService.saveBatch(gameGenreList);
    }

    @Override
    public IPage selectGenrePage(Page<Genre> pageParams, GenreQueryVo genreQueryVo) {
        //1 get condition
        String genrename = genreQueryVo.getGenrename();
        //2 get wrapper
        LambdaQueryWrapper<Genre> wrapper = new LambdaQueryWrapper<>();

        //3 query
        if(!StringUtils.isEmpty(genrename)){
            wrapper.like(Genre::getGenrename, genrename);
        }
        //4 get pagiination
        IPage<Genre> genrePage = baseMapper.selectPage(pageParams, wrapper);

        return genrePage;
    }

    @Override
    public Map<String, Object> getGenreByGameId(Integer gid) {
        //1 get all genres
        List<Genre> allGenreList = baseMapper.selectList(null);
        Map<String, Object> res = new HashMap<>();
        //2 get genres of specific game id
        //2.1 get GameGenre List of gid -> List<GameGenre>
        LambdaQueryWrapper<GameGenre> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GameGenre::getGid, gid);
        List<GameGenre> gameGenreList = gameGenreService.list(wrapper);

        //2.2 get genid List by GameGenre List -> List<Integer>
        List<Integer> genreIdList = gameGenreList.stream()
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
