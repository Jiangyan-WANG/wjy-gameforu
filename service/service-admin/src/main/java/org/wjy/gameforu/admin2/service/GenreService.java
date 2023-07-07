package org.wjy.gameforu.admin2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wjy.gameforu.admin2.entity.Genre;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.vo.gameforu.GenreQueryVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
public interface GenreService extends IService<Genre> {

    void saveGameGenre(Integer gid, Integer[] genids);

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
