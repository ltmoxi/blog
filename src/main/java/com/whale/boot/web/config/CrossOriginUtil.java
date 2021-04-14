package com.whale.boot.web.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.servlet.http.HttpServletResponse;

/**
 * @author litian
 * @date 2017/11/28
 */
public class CrossOriginUtil {
    /**
     * add cross  origin header
     *
     * @param response response
     */
    public static void addCrossOriginHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.setHeader("Access-Control-Allow-Headers",
                "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    /**
     * add cross  origin header
     *
     * @param corsRegistry CorsRegistry
     */
    public static void addCrossOriginHeader(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**");
    }
}
