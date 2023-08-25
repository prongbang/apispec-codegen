package com.prongbang.apispeccodegen

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityScheme

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ApispecCodegenApplication {

    @Bean
    fun customOpenAPI(@Value("\${springdoc.version}") appVersion: String?): OpenAPI? {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearerAuth",
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                    )
                    .addSecuritySchemes(
                        "basicScheme",
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
                    )
            )
            .info(
                Info().title("API-Spec Codegen API").version(appVersion)
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }
}

fun main(args: Array<String>) {
    runApplication<ApispecCodegenApplication>(*args)
}
