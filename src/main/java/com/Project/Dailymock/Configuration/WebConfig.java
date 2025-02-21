package com.Project.Dailymock.Configuration;

import org.opencv.core.Core;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // This loads the OpenCV native library
    }

   /* @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) // Use Swagger 2
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example")) // Replace with your base package
                .paths(PathSelectors.any())
                .build();
    }*/

}
