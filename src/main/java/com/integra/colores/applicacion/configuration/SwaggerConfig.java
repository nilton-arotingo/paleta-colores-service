package com.integra.colores.applicacion.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final String COLORES_CONTROLLER = "Colores" ;
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.integra.colores.aplicacion.controller"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag(COLORES_CONTROLLER, "Tiene los servicios para ver los detalles de api de colores"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API del servicio Paleta de Colores").version("1.0.0").build();
    }    

}
