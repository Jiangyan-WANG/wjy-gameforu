package org.wjy.gameforu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.admin.mapper.GameGenreMapper;
import org.wjy.gameforu.admin.service.GameGenreService;
import org.wjy.gameforu.model.gameforu.GameGenre;

@Service
public class GameGenreServiceImpl extends ServiceImpl<GameGenreMapper, GameGenre> implements GameGenreService {
}
