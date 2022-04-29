package kz.iitu.itse1909.amirlan.application.user.thymeleaf;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import kz.iitu.itse1909.amirlan.kernel.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserTemplateController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "index";
    }

    @RequestMapping(value = {"/socket"}, method = RequestMethod.GET)
    public String socket(Model model) {
        return "socket";
    }

    @RequestMapping(value = {"/registerUser"}, method = RequestMethod.GET)
    public String register(Model model) {
        AppUserForm form = new AppUserForm();
        model.addAttribute("form", form);
        model.addAttribute("error", "");
        return "register";
    }

    @RequestMapping(value = {"/registerUser"}, method = RequestMethod.POST)
    public RedirectView create(Model model, @ModelAttribute("form") AppUserForm form) {
        UserCreateRequestModel userModel = new UserCreateRequestModel();
        userModel.setUsername(form.getUsername());
        userModel.setPassword(form.getPassword());
        userService.createUser(userModel);
        model.addAttribute("error", "");
        model.addAttribute("form", form);
        return new RedirectView("/");
    }
}
