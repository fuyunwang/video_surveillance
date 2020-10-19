package com.fuyunwang.chuoyue.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fuyunwang.chuoyue"))
                .paths(PathSelectors.any())
                .build();
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
}
