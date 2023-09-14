package com.example.anonymous_community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //Swagger에서 스캔할 패키지 범위 RequestHandlerSelectors.Package()메소드를 사용해 설정
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example.anonymous_community"))
                .build();
    }

    //swagger에서 보일 설명 부분
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("anonymous_community REST API with Swagger")
                .description("익명 게시판 REST API 명세서")
                .version("1.0.0")
                .build();


    }

}
