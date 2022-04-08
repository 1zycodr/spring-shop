package kz.iitu.itse1909.amirlan.application.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UploadAvatarResponse;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import kz.iitu.itse1909.amirlan.kernel.FileStorageService;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.InvalidArgumentException;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@RestController
@RequestMapping("api/v1/user")
@Api(tags={"User Controller"})
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, FileStorageService fileStorageService, UserRepository userRepository) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void logPostConstruct() {
        logger.info(UserController.class.getSimpleName() + " constructed!");
    }

    @PreDestroy
    private void logPreDestroy() {
        logger.info(UserController.class.getSimpleName() + " destroying!");
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequestModel user, Errors errors) {
        processErrors(errors);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @ApiOperation(value = "Search a user with an ID", response = AppUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestHeader("host") String host,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "2") int size
    ) {
        logger.info("Request from " + host);
        Pageable paging = PageRequest.of(page, size);
        Page<AppUser> pageTuts = userRepository.findAll(paging);
        List<AppUser> users = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody UserUpdateRequestModel user,
                                        Errors errors
    ) {
        processErrors(errors);
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PutMapping("/{id}/avatar/")
    @ResponseBody
    public UploadAvatarResponse uploadAvatar(@PathVariable Long id,
                                             @RequestParam("file") MultipartFile avatar) {
        String avatarName = fileStorageService.storeFile(avatar);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/user/avatar/")
                .path(avatarName)
                .toUriString();
        AppUser user = userService.getById(id);
        user.setAvatar(fileDownloadUri);
        userService.saveUser(user);
        return new UploadAvatarResponse(avatarName, fileDownloadUri,
                avatar.getContentType(), avatar.getSize());
    }

    @GetMapping("/avatar/{fileName:.*}")
    public ResponseEntity<Resource> downloadAvatar(@PathVariable String fileName,
                                                   HttpServletRequest request) {
        System.out.println("yo");
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    public void processErrors(Errors errors) throws InvalidArgumentException {
        if (errors.hasErrors()) {
            List<String> errorsList = new ArrayList<>();
            for (ObjectError err: errors.getAllErrors()) { errorsList.add(err.getDefaultMessage());}
            throw new InvalidArgumentException(String.join(", ", errorsList));
        }
    }
}
