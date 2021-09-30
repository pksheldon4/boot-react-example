package com.pksheldon4.bootreact.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "example", name = "react-ui-active", havingValue = "true")
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/welcome.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("##### addResourceHandlers()");
        registry.addResourceHandler("/app/**/*")
                .addResourceLocations("classpath:/web")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        log.info("resolving resource " + resourcePath);
                        Resource requestedResource = location.createRelative(resourcePath);
                        log.info("##### Looking for resource: " + requestedResource.toString());
                        // Redirect anything that isn't registered as a route in the API to the react frontend router
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/web/index.html");
                    }
                });
        registry.addResourceHandler("/app/static/**/*")
                .addResourceLocations("classpath:/web/static/")
                .setCacheControl(CacheControl.maxAge(31536000, TimeUnit.SECONDS)) // 1 year
                .resourceChain(true);
    }
}