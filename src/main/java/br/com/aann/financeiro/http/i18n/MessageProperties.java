package br.com.aann.financeiro.http.i18n;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageProperties {

    private static final Locale LOCALE_DEFAULT = new Locale("pt", "BR");

    private final MessageSource messageSource;

    public MessageProperties(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String property, String... args) {
        return messageSource.getMessage(property, args, LOCALE_DEFAULT);
    }

    public String getMessage(String property) {
        return messageSource.getMessage(property, null, LOCALE_DEFAULT);
    }

}