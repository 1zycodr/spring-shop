package kz.iitu.itse1909.amirlan.application.user.controller;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.User;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import kz.iitu.itse1909.amirlan.utils.JsonUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testGetUser() throws Exception {
        User user = User.builder()
            .id(10L)
            .username("test")
            .build();
        when(userService.getById(anyLong())).thenReturn(user);
        Map<String, Object> stringObjectMap = new HashMap<>();
        mockMvc.perform(get("/api/v1/user/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("test")))
                .andExpect(jsonPath("$.id", is(10)));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void createUser() throws Exception {
        User user = User.builder()
                .id(10L)
                .username("test")
                .password("password")
                .build();
        when(userService.createUser(any(UserCreateRequestModel.class))).thenReturn(user);
        UserCreateRequestModel requestModel = new UserCreateRequestModel();
        requestModel.setUsername(user.getUsername());
        requestModel.setPassword(user.getPassword());
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(requestModel)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("test")))
                .andExpect(jsonPath("$.id", is(10)));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void getUsers() throws Exception {
        User user = User.builder()
                .id(10L)
                .username("test")
                .build();
        when(userService.getUsersList()).thenReturn(Arrays.asList(user));
        mockMvc.perform(get("/api/v1/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username", is("test")))
                .andExpect(jsonPath("$[0].id", is(10)));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void updateUser() throws Exception {
        User updatedUser = User.builder()
                .id(10L)
                .username("test")
                .build();
        when(userService.updateUser(anyLong(), any(UserUpdateRequestModel.class)))
                .thenReturn(updatedUser);
        UserUpdateRequestModel requestUpdateModel = new UserUpdateRequestModel();
        UserCreateRequestModel requestCreateModel = new UserCreateRequestModel();
        requestUpdateModel.setUsername("test");
        requestCreateModel.setUsername("testing");
        userService.createUser(requestCreateModel);
        mockMvc.perform(put("/api/v1/user/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(requestUpdateModel)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("test")))
                .andExpect(jsonPath("$.id", is(10)));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/user/100"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
