package com.gbq.boot.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    /**
     * 处理AJAX请求跨域的问题
     * @author 阿前
     * @time 2017-07-13
     */
@Configuration
public class CorsConfig  implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("www.aquestian.cn","*","47.105.100.29","47.105.100.29:8080")
                    .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                    .allowCredentials(true)
                    .maxAge(3600)
                    .allowedHeaders("*");
        }
}
