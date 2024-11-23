package hu.unideb.typeteam.controller;

import hu.unideb.typeteam.dto.UserDto;
import hu.unideb.typeteam.entity.User;
import hu.unideb.typeteam.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login-page";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register-page";
    }

    @PostMapping("/register/save")
    public String registerMember(@Valid @ModelAttribute("user") UserDto userDto,
                                 BindingResult bindingResult, Model model) {


        User existingUserByUserId = userService.findUserByUserId(userDto.getUserId());
        User existingUserByEmail = userService.findUserByEmail(userDto.getEmail());
        if (existingUserByUserId != null) {
            bindingResult.rejectValue("userId", null, "There is already an account with that username");
            return "register-page";
        }
        if (existingUserByEmail != null) {
            bindingResult.rejectValue("email", null, "There is already an account with that email");
            return "register-page";
        }
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", null, "Passwords do not match");
            return "register-page";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register-page";
        }


        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
}
