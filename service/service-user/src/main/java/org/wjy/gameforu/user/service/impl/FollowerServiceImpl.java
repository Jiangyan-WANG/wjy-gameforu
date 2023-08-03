package org.wjy.gameforu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.admin2.service.GameService;
import org.wjy.gameforu.admin2.service.UserService;
import org.wjy.gameforu.model.entity.Comment;
import org.wjy.gameforu.model.entity.Game;
import org.wjy.gameforu.model.entity.User;
import org.wjy.gameforu.user.mapper.FollowerMapper;
import org.wjy.gameforu.model.entity.Follower;
import org.wjy.gameforu.user.service.CommentService;
import org.wjy.gameforu.user.service.FollowerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Service
public class FollowerServiceImpl extends ServiceImpl<FollowerMapper, Follower> implements FollowerService {

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    GameService gameService;

    @Override
    public Map<String, Object> getDynamicsByUid(Integer uid) {
        LambdaQueryWrapper<Follower> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Follower::getFuid,uid);
        List<Follower> follows = baseMapper.selectList(lqw);
        List<User> userList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        List<Game> gameList = new ArrayList<>();
        for (Follower follow : follows) {
            LambdaQueryWrapper<User> userLqw = new LambdaQueryWrapper<>();
            userLqw.eq(User::getId,follow.getUid());
            List<User> followUserList = userService.list(userLqw);
            for (User user : followUserList) {
                LambdaQueryWrapper<Comment> commentLqw = new LambdaQueryWrapper<>();
                commentLqw.eq(Comment::getUid,user.getId());
                //降序排列
                commentLqw.orderByDesc(Comment::getCommentTime);
                List<Comment> followCommentList = commentService.list(commentLqw);
                for (Comment comment : followCommentList) {
                    Game game = gameService.getById(comment.getGid());
                    userList.add(user);
                    commentList.add(comment);
                    gameList.add(game);
                }
            }
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("userList",userList);
        resMap.put("commentList",commentList);
        resMap.put("gameList",gameList);
        return resMap;
    }
}
