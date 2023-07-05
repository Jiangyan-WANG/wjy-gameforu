package org.wjy.gameforu.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.model.gameforu.Game;
import org.wjy.gameforu.model.gameforu.User;
import org.wjy.gameforu.vo.gameforu.GameQueryVo;
import org.wjy.gameforu.vo.gameforu.UserQueryVo;

/**
 * RoleService need to extend the Iservice interface of mp
 * entity class User is set
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
