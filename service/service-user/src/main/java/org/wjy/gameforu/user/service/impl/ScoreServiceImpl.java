package org.wjy.gameforu.user.service.impl;

import org.wjy.gameforu.model.entity.Score;
import org.wjy.gameforu.user.mapper.ScoreMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.user.service.ScoreService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

}
