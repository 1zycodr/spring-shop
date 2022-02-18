package kz.iitu.itse1909.amirlan.application.user.service;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserCreateRequestModel user);
    User updateUser(Long userId, UserUpdateRequestModel user);
    User getById(Long id);
    List<User> getUsersList();
    void deleteUser(Long id);
    void logPostConstruct();
    void logPreDestroy();
}
