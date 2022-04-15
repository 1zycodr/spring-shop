import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import kz.iitu.itse1909.amirlan.kernel.FileStorageService;
import kz.iitu.itse1909.amirlan.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.PathResource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    FileStorageService fileStorageService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    KafkaTemplate<String, String> kafkaTemplate;

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testGetUser() throws Exception {
        AppUser user = AppUser.builder()
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
        AppUser user = AppUser.builder()
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
        AppUser user = AppUser.builder()
                .id(10L)
                .username("test")
                .build();
        when(userService.getUsersList()).thenReturn(Arrays.asList(user));
        when(userRepository.findAll(PageRequest.of(0, 2))).thenReturn(
                new PageImpl<AppUser>(new ArrayList<>()));
        mockMvc.perform(get("/api/v1/user"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void updateUser() throws Exception {
        AppUser updatedUser = AppUser.builder()
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

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testUploadAvatar() throws Exception {
        AppUser user = AppUser.builder()
                .id(1L)
                .username("admin")
                .password("admin")
                .build();
        when(fileStorageService.storeFile(any())).thenReturn("test");
        when(userService.getById(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/user/1/avatar/")
                .file(new MockMultipartFile("file", "file.txt", "text/plain", "value".getBytes())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testDownloadAvatar() throws Exception {
        when(fileStorageService.loadFileAsResource(anyString())).thenReturn(new PathResource("files/uml.png"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/avatar/uml.png"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}