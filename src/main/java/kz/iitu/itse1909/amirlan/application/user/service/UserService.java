package kz.iitu.itse1909.amirlan.application.user.service;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;

import java.util.List;

public interface UserService {
    AppUser createUser(UserCreateRequestModel user);
    AppUser updateUser(Long userId, UserUpdateRequestModel user);
    AppUser getById(Long id);
    List<AppUser> getUsersList();
    void saveUser(AppUser user);
    void deleteUser(Long id);
    void logPostConstruct();
    void logPreDestroy();
}
