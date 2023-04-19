package mx.com.hexlink.es.lifeinbike.apirest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



@Configuration
public class OpenApiConfiguration {
    @Value("${openapi.title}")
    private String title;

    @Value("${openapi.description}")
    private String description;

    @Value("${openapi.version}")
    private String version;



    @Bean
    public OpenAPI openApi(){
        return new OpenAPI().info(
            new Info()
                .title(title)
                .description(description)
                .version(version)
        );
    }
}
