package com.config;

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

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SPRING_WEB)
        	//.host("localhost:5000")
        	.select()
        	.apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/.*"))
            .build().apiInfo(apiEndPointsInfo())
            ;
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("BACKEND")
            .description("Backend para pruebas")
            .contact(new Contact("Servicio de IT", "", "tino1997@hotmail.com"))
            .license("BACKEND")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("2.0.0")
            .build();
    }
}