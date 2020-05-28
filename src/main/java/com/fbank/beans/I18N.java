package com.fbank.beans;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class I18N {
    MessageSource messageSource;

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/language");
        messageSource.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        return messageSource;
    }
}
