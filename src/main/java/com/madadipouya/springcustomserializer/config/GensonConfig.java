package com.madadipouya.springcustomserializer.config;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.spring.GensonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GensonConfig {

    @Bean
    public Genson createGenson() {
        return new GensonBuilder()
                .setHtmlSafe(true)
                .setSkipNull(true)
                .useBeanViews(true)
                .create();
    }

    @Bean
    public GensonMessageConverter createGensonMessageConverter() {
        return new GensonMessageConverter(createGenson());
    }
}
