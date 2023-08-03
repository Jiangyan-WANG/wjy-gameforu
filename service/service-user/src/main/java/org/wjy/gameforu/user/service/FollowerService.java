package org.wjy.gameforu.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.wjy.gameforu.model.entity.Follower;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
public interface FollowerService extends IService<Follower> {
    Map<String,Object> getDynamicsByUid(Integer uid);
}
