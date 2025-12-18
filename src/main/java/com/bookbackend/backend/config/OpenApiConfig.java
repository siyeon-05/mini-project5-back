package com.bookbackend.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // 🔐 Security Scheme 이름 정의
        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                // 🔐 JWT SecurityScheme 등록
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                        )
                )
                // 🔐 모든 API 기본적으로 Security 필요하도록 설정
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                // 기존 정보 유지
                .info(new Info()
                        .title("Book Backend API 문서")
                        .version("1.0.0")
                        .description("도서 관리 백엔드 서비스의 REST API 문서입니다.")
                );
    }
}
