package br.com.aann.financeiro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ParameterSpecificationContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${aplicacao.key:api-key}")
    private String key;

    @Value("${aplicacao.token:aXRhw7o=}")
    private String token;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .globalRequestParameters(Collections.singletonList(this.authorizationParameter()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.aann"))
                .paths(PathSelectors.any())
                .build();
    }

    private RequestParameter authorizationParameter() {
        RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
        tokenBuilder
                .name(key)
                .description("Token de acesso Ex.:" + token)
                .required(true)
                .in(ParameterType.HEADER)
                .accepts(Collections.singleton(MediaType.APPLICATION_JSON))
                .build();
        return tokenBuilder.build();
    }

}
