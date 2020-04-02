package ru.alexsumin.springcourse.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocalizationProvider {
    private final MessageSource messageSource;
    private final Locale locale;

    public LocalizationProvider(MessageSource messageSource, @Value("${user.locale}")String localeTag) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localeTag);
    }

    public String getLocalized(String required) {
        return messageSource.getMessage(required, null, locale);
    }
}
