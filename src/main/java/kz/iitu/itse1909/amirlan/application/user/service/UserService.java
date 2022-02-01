package kz.iitu.itse1909.amirlan.application.user.service;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.User;

public interface UserService {
    User create(UserRequestModel user);
}
