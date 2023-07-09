package org.wjy.gameforu.admin2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wjy.gameforu.model.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.vo.GameQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
public interface GameService extends IService<Game> {
    /**
     * conditional search
     * @param pageParam
     * @param gameQueryVo
     * @return
     */
    IPage selectGamePage(Page<Game> pageParam, GameQueryVo gameQueryVo);

}
