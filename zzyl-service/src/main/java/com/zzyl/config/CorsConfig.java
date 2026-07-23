package com.zzyl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 * <p>
 * 修改点：整体重写，修复两个问题：
 * 1. 原配置使用的是 WebFlux 响应式版 {@code org.springframework.web.cors.reactive.CorsWebFilter}，
 *    本项目为 Servlet(Spring MVC) 应用，响应式 WebFilter 不会被注册到 Servlet 过滤器链，
 *    该 Bean 实际是"死配置"、完全不生效。现改用 Servlet 版 {@link CorsFilter}，使配置真正生效。
 * 2. 允许来源原为硬编码通配符 "*"。现改为通过配置项 {@code zzyl.cors.allowed-origins} 注入，
 *    默认值保持 "*" 以兼容现有开发联调行为；生产环境应在 application.yml 中显式配置
 *    为具体域名（多个用逗号分隔），例如：
 *    zzyl:
 *      cors:
 *        allowed-origins: https://admin.example.com,https://m.example.com
 * <p>
 * 注意：未开启 allowCredentials（凭证跨域），如后续需要携带 Cookie，
 * 必须同时把 allowed-origins 配置为具体域名，浏览器不允许 "*" 与凭证同时使用。
 */
@Configuration
public class CorsConfig {

    /**
     * 允许跨域的来源列表，逗号分隔；默认 "*"（仅建议开发环境使用）
     */
    @Value("${zzyl.cors.allowed-origins:*}")
    private String[] allowedOrigins;

    /**
     * 注册 Servlet 版跨域过滤器
     *
     * @return CorsFilter 跨域过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有的请求方法
        config.addAllowedMethod("*");
        // 允许的来源：使用 OriginPattern 以同时兼容 "*" 与具体域名/通配子域写法
        for (String origin : allowedOrigins) {
            config.addAllowedOriginPattern(origin.trim());
        }
        // 允许所有的请求头
        config.addAllowedHeader("*");
        // 预检请求缓存时间（秒），减少 OPTIONS 请求次数
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 针对所有的请求都支持跨域
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
