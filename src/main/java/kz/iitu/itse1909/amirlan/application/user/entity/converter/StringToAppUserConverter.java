package kz.iitu.itse1909.amirlan.application.user.entity.converter;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import org.springframework.core.convert.converter.Converter;


public class StringToAppUserConverter implements Converter<String, AppUser> {
    @Override
    public AppUser convert(String source) {
        String[] data = source.split(",");
        AppUser user = new AppUser();
        user.setId(Long.parseLong(data[0]));
        user.setUsername(data[1]);
        user.setPassword(data[2]);
        return user;
    }
}
