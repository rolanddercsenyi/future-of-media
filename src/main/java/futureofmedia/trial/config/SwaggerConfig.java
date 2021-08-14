package futureofmedia.trial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * Beállítások swaggerhez
     *
     * @return api adatok visszaadása
     */
    private ApiInfo apiInfo() {
        return new ApiInfo("REST API",
                "Future of Media test feladathoz swagger",
                "1.0",
                "Terms of service",
                new Contact("Dercsényi Roland", "", "roland.dercsenyi@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    /**
     * @return swagger futattáshoz beállítások
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Swagger használatához apiKey beillesztése headerbe
     *
     * @return apiKey
     */
    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    /**
     * Swaggerhez default header beállítása
     *
     * @return securityContext
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    /**
     * Swaggerhez default Authentikácó beálltása
     *
     * @return defaultAuth
     */
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}