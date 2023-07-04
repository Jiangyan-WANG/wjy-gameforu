package org.wjy.gameforu.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.wjy.gameforu.model.gameforu.Game;
import org.wjy.gameforu.model.gameforu.User;

@Repository
public interface GameMapper extends BaseMapper<Game> {
}
