package com.copa2026.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração global de CORS (Cross-Origin Resource Sharing).
 *
 * Libera o acesso à API a partir do frontend React, que roda em
 * http://localhost:3000 durante o desenvolvimento. Sem essa configuração,
 * o navegador bloquearia as requisições do frontend para o backend
 * (origens diferentes: 3000 x 8080).
 *
 * IMPORTANTE: configurar isso ANTES de testar a integração com o frontend.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000") // origem do frontend React
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
