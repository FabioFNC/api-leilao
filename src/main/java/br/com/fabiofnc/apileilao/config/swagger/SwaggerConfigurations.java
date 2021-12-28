package br.com.fabiofnc.apileilao.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fabiofnc.apileilao.entity.Usuario;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket forumApi() {
        //Passando o tipo de documentacao
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //Pacote onde a leitura sera feita
                .apis(RequestHandlerSelectors.basePackage("br.com.fabiofnc.apileilao"))
                .paths(PathSelectors.ant("/**"))
                .build()
                /* 
                    Como a classe Usuario possui atributos relacionados ao login, senha e perfis de acesso, não é recomendado que essas informações sejam expostas na documentação do Swagger.
                */
                .ignoredParameterTypes(Usuario.class)
                .globalRequestParameters(Arrays.asList(
                  new RequestParameterBuilder()
                  .name("Authorization")
                  .description("Header para Token JWT")
                  .required(false)
                  .in("header")
                  .build()));
    }
    
}