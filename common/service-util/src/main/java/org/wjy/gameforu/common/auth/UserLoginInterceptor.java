package org.wjy.gameforu.common.auth;

import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.wjy.gameforu.common.constant.RedisConst;
import org.wjy.gameforu.common.utils.JwtHelper;
import org.wjy.gameforu.vo.LoginDataVo;
import org.wjy.gameforu.vo.UserInfoResponseVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * intercepter for authorization
 * implement interface of extends abs class
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    private RedisTemplate redisTemplate;
    public UserLoginInterceptor(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        this.getUserLoginInfo(request);
        return true;
    }

    private void getUserLoginInfo(HttpServletRequest request) {
        // get token from header
        String token = request.getHeader("token");
        log.info("test interceptor: token");
        // get userId from token
        if(!StringUtils.isEmpty(token)){
            Long userId = JwtHelper.getUserId(token);
            // get userInfo by userId from redis
            UserInfoResponseVo userInfoResponseVo =  (UserInfoResponseVo) redisTemplate.opsForValue().get(
                    RedisConst.USER_LOGIN_KEY_PREFIX + userId);
            // set userInfo to ThreadLocal
            if(userInfoResponseVo!=null){
                AuthContextHolder.setUserId(userId.intValue());
                AuthContextHolder.setUsername(userInfoResponseVo.getUsername());
                AuthContextHolder.setAvatar(userInfoResponseVo.getAvatar());
                log.info("test interceptor: " + AuthContextHolder.getUserId());
                log.info("test interceptor: " + AuthContextHolder.getUsername());
            }
        }


    }
}
