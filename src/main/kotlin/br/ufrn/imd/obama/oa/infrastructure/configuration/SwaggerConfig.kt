package br.ufrn.imd.obama.oa.infrastructure.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    private val bearerAuth = "Bearer Authentication"

    @Bean
    fun openAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info()
                    .version("1.0")
                    .title("Objetos de Aprendizagem para Matemática (OBAMA)")
                    .description("Uma aplicação para procurar objetos de aprendizagem")
                    .contact(Contact().name("Obama").email("obama@imd.ufrn.br"))
                    .license(License().name("Creative Commons Attribution 4.0 International License"))
            )

            .addSecurityItem(
                SecurityRequirement()
                    .addList(bearerAuth)
            )

            .components(
                Components()
                    .addSecuritySchemes(
                        bearerAuth,
                        createAPIKeyScheme()
                    )
            )
    }

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("controllers")
            .pathsToMatch("/v1/**")  // Ajuste conforme necessário
            .build()
    }

    private fun createAPIKeyScheme(): SecurityScheme {
        return  SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
    }
}
