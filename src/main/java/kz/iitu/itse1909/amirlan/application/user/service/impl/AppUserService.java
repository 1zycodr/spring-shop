package kz.iitu.itse1909.amirlan.application.user.service.impl;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.User;
import kz.iitu.itse1909.amirlan.application.user.exceptions.UserAlreadyExistsException;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserCreateRequestModel requestUser) {
        if (userRepository.findUserByUsername(requestUser.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }
        User user = User.builder()
                .username(requestUser.getUsername())
                .password(passwordEncoder.encode(requestUser.getPassword()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserUpdateRequestModel requestUser) throws EntityNotFoundException {
        if (userRepository.existsById(id)) {
            User updateUser = userRepository.getById(id);
            if (!updateUser.getUsername().equals(requestUser.getUsername())) {
                if (userRepository.findUserByUsername(requestUser.getUsername()) != null) {
                    throw new UserAlreadyExistsException();
                } else {
                    updateUser.setUsername(requestUser.getUsername());
                }
            }
            if (requestUser.getPassword() != null) {
                updateUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
            }
            return userRepository.save(updateUser);
        } else {
            throw new EntityNotFoundException(String.valueOf(id));
        }
    }

    @Override
    public User getById(Long id) throws EntityNotFoundException {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException(String.valueOf(id));
        }
    }

    @Override
    public List<User> getUsersList() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
