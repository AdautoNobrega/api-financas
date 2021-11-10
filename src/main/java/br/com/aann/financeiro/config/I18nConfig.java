package br.com.aann.financeiro.config;

import br.com.aann.financeiro.http.i18n.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
public class I18nConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages");
        source.setDefaultEncoding("UTF-8");
        source.setCacheSeconds(1);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    @Autowired
    public MessageProperties messagePropertiesResolver(MessageSource messageSource) {
        return new MessageProperties(messageSource);
    }
}