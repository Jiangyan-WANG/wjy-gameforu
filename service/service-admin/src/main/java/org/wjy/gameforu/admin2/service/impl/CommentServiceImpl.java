package org.wjy.gameforu.admin2.service.impl;

import org.wjy.gameforu.model.entity.Comment;
import org.wjy.gameforu.admin2.mapper.CommentMapper;
import org.wjy.gameforu.admin2.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
