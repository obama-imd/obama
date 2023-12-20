package br.ufrn.imd.obama.oa.infrastructure.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .version("1.0")
                                .title("Objetos de Aprendizagem para Matemática (OBAMA)")
                                .description("Uma aplicação para procurar objetos de aprendizagem")
                                .contact(new Contact().name("Vitor Henrique Coelho Bezerra").email("vitorhenrique908@gmail.com"))
                                .license(new License().name("Creative Commons Attribution 4.0 International License"))

                );
    }

}
