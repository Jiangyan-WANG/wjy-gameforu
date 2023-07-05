package org.wjy.gameforu.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.model.gameforu.Genre;
import org.wjy.gameforu.vo.gameforu.GenreQueryVo;

import java.util.List;
import java.util.Map;

public interface GenreService extends IService<Genre> {

    void saveGameGenre(Integer gid, List<Integer> genids);

    /**
     *
     * @param pageParams
     * @param genreQueryVo
     * @return
     */
    IPage selectGenrePage(Page<Genre> pageParams, GenreQueryVo genreQueryVo);


    /**
     * get all genres and genres of specific game
     * @param gid
     * @return
     */
    Map<String, Object> getGenreByGameId(Integer gid);
}
