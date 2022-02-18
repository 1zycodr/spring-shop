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
import java.util.Optional;

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
    public void logPostConstruct() {
        String logMessage = AppUserService.class.getSimpleName() + " constructed!";
        logger.info(logMessage);
        // here we can see test_value in console
        System.out.println(testValue);
    }

    @PreDestroy
    public void logPreDestroy() {
        String logMessage = AppUserService.class.getSimpleName() + " destroying!";
        logger.info(logMessage);
    }

    @Override
    public User createUser(UserCreateRequestModel requestUser) {
        User userRep = userRepository.findUserByUsername(requestUser.getUsername());
        if (userRep != null) {
            throw new UserAlreadyExistsException();
        }
        User user;
        user = User.builder()
            .username(requestUser.getUsername())
            .password(
                    passwordEncoder.encode(requestUser.getPassword())
            )
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
            String password = requestUser.getPassword();
            if (password != null) {
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
            Optional<User> user = userRepository.findById(id);
            return user.get();
        } else {
            String idString = String.valueOf(id);
            throw new EntityNotFoundException(idString);
        }
    }

    @Override
    public List<User> getUsersList() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        if (id >= 0) {
            userRepository.deleteById(id);
        }
    }
}
