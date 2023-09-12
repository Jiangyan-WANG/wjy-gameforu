package org.wjy.gameforu.recommender.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.Score;
import org.wjy.gameforu.model.entity.ThumbsGame;
import org.wjy.gameforu.model.entity.UserGameRating;
import org.wjy.gameforu.user.service.ScoreService;
import org.wjy.gameforu.user.service.ThumbsGameService;
import org.wjy.gameforu.recommender.service.UserGameRatingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/usergamerating/recommender")
@Api(tags = "genre management")
@CrossOrigin
public class UserGameRatingController {

    @Autowired
    private UserGameRatingService userGameRatingService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ThumbsGameService thumbsGameService;

    @GetMapping("reimport")
    public Result reImportAll(){
        // 如果新增了用户对游戏的评分，加入rating表中
        List<Score> allScores = scoreService.list();
        for (Score score : allScores) {
            LambdaQueryWrapper<UserGameRating> userGameRatingLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userGameRatingLambdaQueryWrapper.eq(UserGameRating::getUid,score.getUid());
            userGameRatingLambdaQueryWrapper.eq(UserGameRating::getGid,score.getGid());
            UserGameRating scoreToRating = userGameRatingService.getOne(userGameRatingLambdaQueryWrapper);
            if(scoreToRating == null){
                UserGameRating userGameRating = new UserGameRating();
                userGameRating.setUid(score.getUid());
                userGameRating.setGid(score.getGid());
                userGameRatingService.save(userGameRating);
            }

        }

        // 如果新增了用户对游戏的点赞，加入rating表中
        List<ThumbsGame> allThumbs = thumbsGameService.list();
        for (ThumbsGame thumbsGame : allThumbs) {
            LambdaQueryWrapper<UserGameRating> userGameRatingLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userGameRatingLambdaQueryWrapper.eq(UserGameRating::getUid, thumbsGame.getUid());
            userGameRatingLambdaQueryWrapper.eq(UserGameRating::getGid, thumbsGame.getGid());
            UserGameRating thumbsToRating = userGameRatingService.getOne(userGameRatingLambdaQueryWrapper);
            if (thumbsToRating == null) {
                UserGameRating userGameRating = new UserGameRating();
                userGameRating.setUid(thumbsGame.getUid());
                userGameRating.setGid(thumbsGame.getGid());
                userGameRatingService.save(userGameRating);
            }
        }

        // 计算rating
        // 得到所有需要计算的uesr-game
        List<UserGameRating> ratings = userGameRatingService.list();
        // map记录平均分，平均点赞，以及最大减最小
        Map<Integer, Double> avgScore = new HashMap<>();
        Map<Integer, Double> diffScore = new HashMap<>();
        Map<Integer, Double> avgThumb = new HashMap<>();
        Map<Integer, Double> diffThumb = new HashMap<>();

        for (UserGameRating rating : ratings) {
            //分数列表
            LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
            scoreLambdaQueryWrapper.eq(Score::getUid, rating.getUid());
            List<Score> scores = scoreService.list(scoreLambdaQueryWrapper);

            if(!avgScore.containsKey(rating.getUid())){
                avgScore.put(rating.getUid(), getAvgScore(scores));
                diffScore.put(rating.getUid(), getDiffScore(scores));
            }

            //点赞列表
            LambdaQueryWrapper<ThumbsGame> thumbsGameLambdaQueryWrapper = new LambdaQueryWrapper<>();
            thumbsGameLambdaQueryWrapper.eq(ThumbsGame::getUid, rating.getUid());
            List<ThumbsGame> thumbsGames = thumbsGameService.list(thumbsGameLambdaQueryWrapper);

            if(!avgThumb.containsKey(rating.getUid())){
                avgThumb.put(rating.getUid(), getAvgThumbsGame(thumbsGames));
                diffThumb.put(rating.getUid(), getDiffThumbsGame(thumbsGames));
            }

            //计算标准化分数与点赞分，并求rating
            //score
            rating.setRating(0d);
            scoreLambdaQueryWrapper.eq(Score::getGid,rating.getGid());
            Score score = scoreService.getOne(scoreLambdaQueryWrapper);
            if(score!=null){

                rating.setRating(
                        (
                                score.getScore()-avgScore.get(score.getUid())
                        )/
                                diffScore.get(score.getUid()) * 0.7
                );
            }

            //thumbs
            thumbsGameLambdaQueryWrapper.eq(ThumbsGame::getGid,rating.getGid());
            ThumbsGame thumbsGame = thumbsGameService.getOne(thumbsGameLambdaQueryWrapper);
            if(thumbsGame != null){
                rating.setRating(rating.getRating() +
                                (
                                        (thumbsGame.getUp()?1:-1)-avgScore.get(thumbsGame.getUid())
                                )/
                                        diffScore.get(thumbsGame.getUid()) * 0.3
                        );

            }
            userGameRatingService.updateById(rating);
        }

        //归一化
        NormOne();

        return Result.ok(null);
    }

    public Double getAvgScore(List<Score> scores){
        if(scores == null || scores.stream().count()==0) return 0d;
        return scores.stream().mapToDouble(Score::getScore).average().getAsDouble();
    }

    public Double getDiffScore(List<Score> scores){
        if(scores == null || scores.stream().count()<=1) return 9d;
        Double diffScore = scores.stream().mapToDouble(Score::getScore).max().getAsDouble() -
                scores.stream().mapToDouble(Score::getScore).min().getAsDouble();
        if(diffScore==0) return 9d;
        return diffScore;
    }

    public Double getAvgThumbsGame(List<ThumbsGame> thumbsGames){
        if(thumbsGames == null || thumbsGames.stream().count()==0) return 0d;
        return thumbsGames.stream().map(item->item.getUp()?1:-1).collect(Collectors.toList()).stream()
                .mapToDouble(Integer::doubleValue).
                average().getAsDouble();
    }

    public Double getDiffThumbsGame(List<ThumbsGame> thumbsGames){
        if(thumbsGames == null || thumbsGames.stream().count()<=1) return 2d;
        List<Integer> intThumbsGames = thumbsGames.stream().map(item -> item.getUp() ? 1 : -1).collect(Collectors.toList());
        Double diffThumbsGames = intThumbsGames.stream().mapToDouble(Integer::doubleValue).max().getAsDouble()-
                intThumbsGames.stream().mapToDouble(Integer::doubleValue).min().getAsDouble();
        if(diffThumbsGames==0) return 2d;
        return diffThumbsGames;
    }

    public void NormOne(){
        List<UserGameRating> ratings = userGameRatingService.list();
        if(ratings == null || ratings.stream().count() <= 1) return;
        double maxRating = ratings.stream().mapToDouble(UserGameRating::getRating).max().getAsDouble();
        double minRating = ratings.stream().mapToDouble(UserGameRating::getRating).min().getAsDouble();
        for (UserGameRating rating : ratings) {
            double ratingScore = rating.getRating();
            rating.setRating(
                    (ratingScore-minRating)
                    / (maxRating-minRating)
            );
            userGameRatingService.updateById(rating);
        }
    }
}

