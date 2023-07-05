package org.wjy.gameforu.acl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.acl.mapper.GameGenreMapper;
import org.wjy.gameforu.acl.service.GameGenreService;
import org.wjy.gameforu.model.gameforu.GameGenre;


@Service
public class GameGenreServiceImpl extends ServiceImpl<GameGenreMapper, GameGenre> implements GameGenreService {

}
