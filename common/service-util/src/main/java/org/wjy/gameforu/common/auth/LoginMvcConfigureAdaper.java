package org.wjy.gameforu.common.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
//        List<String> excludePath = new ArrayList<>();
//        excludePath.add("/*/permission/login/**");
//        // exclude swagger2 related path
////        "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**"
//        excludePath.add("/swagger-ui.html");
//        excludePath.add("/webjars/**");
//        excludePath.add("/v2/**");
//        excludePath.add("/swagger-resources/**");
        registry.addInterceptor(new UserLoginInterceptor(redisTemplate))
                .addPathPatterns("/**")
                .excludePathPatterns("/*/permission/login/**", "/error", "/*/search/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /**
         * 配置swagger-ui显示文档
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        /** 公共部分内容 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
