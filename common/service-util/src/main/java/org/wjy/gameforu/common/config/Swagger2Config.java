package org.wjy.gameforu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2 configuration
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    /**
     * api config for web user
     * @return
     */
    @Bean
    public Docket webApiConfig(){
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("userId")
                .description("用户token")
                //.defaultValue(JwtHelper.createToken(1L, "admin"))
                .defaultValue("1")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());

        Docket webApi = new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                // regex to constraint: only show api start with /api/
                .apis(RequestHandlerSelectors.basePackage("org.wjy.gameforu"))
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .globalOperationParameters(pars);
        return webApi;
    }

    /**
     * api config for admin
     * @return
     */
    @Bean
    public Docket adminApiConfig(){
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("adminId")
                .description("用户token")
                .defaultValue("1")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());

        Docket adminApi = new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                // only show path with /admin/
                .apis(RequestHandlerSelectors.basePackage("org.wjy.gameforu"))
                .paths(PathSelectors.regex("/.*/admin/.*"))
                .build()
                .globalOperationParameters(pars);
        return adminApi;
    }

    /**
     * api config for permission
     * @return
     */
    @Bean
    public Docket permissionApiConfig(){
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("permissionId")
                .description("用户token")
                .defaultValue("1")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());

        Docket permissionApi = new Docket(DocumentationType.SWAGGER_2)
                .groupName("permissionApi")
                .apiInfo(permissionApiInfo())
                .select()
                // only show path with /admin/
                .apis(RequestHandlerSelectors.basePackage("org.wjy.gameforu"))
                .paths(PathSelectors.regex("/.*/permission/.*"))
                .build()
                .globalOperationParameters(pars);
        return permissionApi;
    }

    /**
     * api info
     * @return
     */
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文档描述了GameForU服务接口定义")
                .version("1.0")
                //联系方式
                .contact(new Contact("J Wang", "", "jxw1466@alumni.bham.ac.uk"))
                .build();
    }

    /**
     * api info
     * @return
     */
    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文档描述了GameForU后台系统服务接口定义")
                .version("1.0")
                .contact(new Contact("J Wang", "", "jxw1466@alumni.bham.ac.uk"))
                .build();
    }

    /**
     * api info
     * @return
     */
    private ApiInfo permissionApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文档描述了GameForU后台系统服务接口定义")
                .version("1.0")
                .contact(new Contact("J Wang", "", "jxw1466@alumni.bham.ac.uk"))
                .build();
    }
}