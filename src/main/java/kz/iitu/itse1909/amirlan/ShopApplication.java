package kz.iitu.itse1909.amirlan;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.cache.CacheManager;
import java.util.Arrays;

@SpringBootApplication
public class ShopApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ShopApplication.class, args);
    }
}
