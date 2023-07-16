package org.wjy.gameforu.user.service;

import org.wjy.gameforu.model.entity.Prefer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
public interface PreferService extends IService<Prefer> {

    Boolean setPrefer(Integer uid, Integer[] preferIds);

    Map<String, Object> getPreferByUid(Integer uid);
}
