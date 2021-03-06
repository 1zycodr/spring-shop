package kz.iitu.itse1909.amirlan.application.user.service.impl;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.exceptions.UserAlreadyExistsException;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.EntityNotFoundException;
import kz.iitu.itse1909.amirlan.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AppUserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    AuthenticationManager authenticationManager;

    private UserService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new AppUserService(userRepository, passwordEncoder, jwtTokenProvider, authenticationManager);
    }

    @Test
    void logTests() {
        Assertions.assertDoesNotThrow( () -> {
            this.service.logPreDestroy();
            this.service.logPostConstruct();
        });
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void createUser() {
        AppUser user = AppUser.builder()
                .username("testt")
                .password("password")
                .build();
        when(userRepository.save(any(AppUser.class))).thenReturn(user);
        UserCreateRequestModel requestModel = new UserCreateRequestModel();
        requestModel.setUsername(user.getUsername());
        requestModel.setPassword(user.getPassword());
        AppUser serviceUser = service.createUser(requestModel);
        Assertions.assertNotNull(serviceUser);
        Assertions.assertEquals(serviceUser.getUsername(), user.getUsername());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void createUserThrow() {
        AppUser user = AppUser.builder()
                .username("admin")
                .password("password")
                .build();
        when(userRepository.findUserByUsername(anyString())).thenReturn(user);
        UserCreateRequestModel requestModel = new UserCreateRequestModel();
        requestModel.setUsername(user.getUsername());
        requestModel.setPassword(user.getPassword());
        Assertions.assertThrowsExactly(
                UserAlreadyExistsException.class,
                () -> service.createUser(requestModel)
        );
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void updateUser() {
        AppUser user = AppUser.builder()
                .username("test_user")
                .password("password")
                .build();
        UserUpdateRequestModel userModel = new UserUpdateRequestModel();
        userModel.setUsername("test_user_renamed");
        userModel.setPassword("password");

        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(userRepository.getById(anyLong())).thenReturn(user);
        when(userRepository.findUserByUsername(anyString())).thenReturn(null);
        when(userRepository.save(any(AppUser.class))).thenReturn(
                AppUser.builder().id(10L).username("test_user_renamed").build());

        AppUser updatedUser = service.updateUser(10L, userModel);
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(updatedUser.getUsername(), "test_user_renamed");
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void updateUserThrowAlreadyExists() {
        UserUpdateRequestModel userModel = new UserUpdateRequestModel();

        when(userRepository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrowsExactly(
                EntityNotFoundException.class,
                () -> service.updateUser(10L, userModel)
        );
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void updateUserThrowNotFound() {
        AppUser user = AppUser.builder()
                .username("test_user")
                .password("password")
                .build();
        UserUpdateRequestModel userModel = new UserUpdateRequestModel();
        userModel.setUsername("test_user_renamed");
        userModel.setPassword("password");

        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(userRepository.getById(anyLong())).thenReturn(user);
        when(userRepository.findUserByUsername(anyString())).thenReturn(user);

        Assertions.assertThrowsExactly(
                UserAlreadyExistsException.class,
                () -> service.updateUser(10L, userModel)
        );
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void getById() {
        AppUser user = AppUser.builder()
                .username("test_user")
                .password("password")
                .build();
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        AppUser retrievedUser = service.getById(1L);
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(retrievedUser.getUsername(), user.getUsername());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void getByIdThrows() {
        Assertions.assertThrowsExactly(
                EntityNotFoundException.class,
                () -> service.getById(1L)
        );
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void getUsersList() {
        AppUser user = AppUser.builder()
                .username("test_user")
                .password("password")
                .build();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<AppUser> users = service.getUsersList();
        Assertions.assertEquals(users.size(), 1);
        Assertions.assertEquals(users.get(0), user);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void deleteUser() {
        service.deleteUser(10L);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void saveUser() {
        AppUser user = AppUser.builder()
                .username("test_user")
                .password("password")
                .build();
        Assertions.assertDoesNotThrow(() -> {
            service.saveUser(user);
        });
    }
}