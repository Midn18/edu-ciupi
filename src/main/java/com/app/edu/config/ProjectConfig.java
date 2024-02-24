package com.app.edu.config;

import com.app.edu.utils.SoundManager;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ProjectConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
            .title("Edu Ciupi API")
            .version("1.0")
            .description("This API exposes endpoints for Edu Ciupi Educational App.")
            .license(mitLicense);

        return new OpenAPI().info(info);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SoundManager soundManager() {
        return SoundManager.getInstance();
    }
}
