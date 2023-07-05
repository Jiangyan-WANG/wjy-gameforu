package org.wjy.gameforu.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.acl.mapper.GenreMapper;
import org.wjy.gameforu.acl.service.GenreService;
import org.wjy.gameforu.model.gameforu.Genre;
import org.wjy.gameforu.vo.gameforu.GenreQueryVo;

@Service
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre> implements GenreService {
    @Override
    public IPage selectGenrePage(Page<Genre> pageParams, GenreQueryVo genreQueryVo) {
        //1 get condition
        String genrename = genreQueryVo.getGenrename();
        //2 get wrapper
        LambdaQueryWrapper<Genre> wrapper = new LambdaQueryWrapper<>();

        //3 query
        if(!StringUtils.isEmpty(genrename)){
            wrapper.like(Genre::getGenreName, genrename);
        }
        //4 get pagiination
        IPage<Genre> genrePage = baseMapper.selectPage(pageParams, wrapper);

        return genrePage;
    }
}
