package org.wjy.gameforu.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.model.gameforu.Genre;
import org.wjy.gameforu.vo.gameforu.GenreQueryVo;

public interface GenreService extends IService<Genre> {

    /**
     *
     * @param pageParams
     * @param genreQueryVo
     * @return
     */
    IPage selectGenrePage(Page<Genre> pageParams, GenreQueryVo genreQueryVo);
}
