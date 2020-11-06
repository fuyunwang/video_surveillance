package com.fuyunwang.chuoyue.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/29 18:57
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("Global token for authentication")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fuyunwang.chuoyue"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Chuoyue——绰约 后台接口文档")
                .contact(new Contact("FuyunWang", "https://github.com/fuyunwang", "beautifulsoup@163.com"))
                .description("Chuoyue即绰约——凌波绰约无尘俗 不羡群芳自展颜")
                .termsOfServiceUrl("https://github.com/fuyunwang/Chuoyue-SpringBoot")
                .version("2.0")
                .build();
    }



    /*public List<ApiKey> securitySchemes(){
        List<ApiKey> apiKeys=new ArrayList<>();
        apiKeys.add(new ApiKey("oauth2 认证","Authorization","bearer "));

        return apiKeys;
    }

    public List<SecurityContext> securityContexts(){
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(securityReferences())
                .forPaths(PathSelectors.any()).build());

        return securityContexts;
    }

    public List<SecurityReference> securityReferences(){
        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0]=new AuthorizationScope("global","accessEverything");

        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization",authorizationScopes));

        return securityReferences;
    }*/

}
