package com.mikhail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Конфигурирование swagger.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API Reference")
                .version("1.0.0")
                .build();
    }

    /**
     * Конфигурирование swagger сервиса
     *
     * @return Docket объект с настройками swagger
     */
    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(newArrayList(apiKey()))
                .select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.mikhail"))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
}
