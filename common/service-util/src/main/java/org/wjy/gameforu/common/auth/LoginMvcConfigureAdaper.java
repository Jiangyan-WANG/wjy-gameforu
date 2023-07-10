package org.wjy.gameforu.common.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * config for interceptor
 */
@Configuration
public class LoginMvcConfigureAdaper extends WebMvcConfigurationSupport  {

    @Resource
    RedisTemplate redisTemplate;

    /**
     * config intercept path
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor(redisTemplate))
                .addPathPatterns("/**")
                .excludePathPatterns("/*/permission/login/**");
        super.addInterceptors(registry);
    }
}
