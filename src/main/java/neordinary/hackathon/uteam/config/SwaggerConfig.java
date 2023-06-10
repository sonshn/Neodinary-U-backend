package neordinary.hackathon.uteam.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI eateryApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Eatery API Docs")
                                .description("너디너리 해커톤 4th 프로젝트")
                                .version("0.0.1")
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Github organization of team u")
                                .url("https://github.com/Neodinary-U")
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "access-token",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("Bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
