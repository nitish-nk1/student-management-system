package in.strikes.crudSpringBootDemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studentManagementAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Student Management System API")
                        .description("REST APIs for managing student records")
                        .version("1.0.0"));
    }
}