package kz.iitu.itse1909.amirlan.application.user.service.impl;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.User;
import kz.iitu.itse1909.amirlan.application.user.exceptions.UserAlreadyExistsException;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@PropertySource("classpath:application.properties")
public class AppUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Value("${custom.property}")
    private String testValue;

    public AppUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void logPostConstruct() {
        logger.info(AppUserService.class.getSimpleName() + " constructed!");
        // here we can see test_value in console
        System.out.println(testValue);
    }

    @PreDestroy
    private void logPreDestroy() {
        logger.info(AppUserService.class.getSimpleName() + " destroying!");
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
