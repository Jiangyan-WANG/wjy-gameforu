package org.wjy.gameforu.recommender.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wjy.gameforu.recommender.mapper.UserGameRatingMapper;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.model.entity.UserGameRating;
import org.wjy.gameforu.recommender.service.UserGameRatingService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class UserGameRatingServiceImpl extends ServiceImpl<UserGameRatingMapper, UserGameRating> implements UserGameRatingService {

}
