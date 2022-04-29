package kz.iitu.itse1909.amirlan.application.user.thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserForm {
    private String username;
    private String password;
    private String avatar;
}
