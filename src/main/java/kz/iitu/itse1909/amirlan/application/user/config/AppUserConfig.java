package kz.iitu.itse1909.amirlan.application.user.config;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.entity.converter.StringToAppUserConverter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class AppUserConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Lazy
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(
                new HashSet<>(){{
                    add(new StringToAppUserConverter());
                }}
        );
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    @Bean
    public void demo() {
        ConversionService conversionService = conversionService().getObject();
        String password = passwordEncoder.encode("password");
        AppUser user = conversionService.convert("1,username," + password, AppUser.class);
        System.out.println("Converted user: " + user.toString());
    }
}
