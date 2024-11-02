package hu.unideb.typeteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("loginPage")
    public String loginPage() {
        return "login-page";
    }

    @GetMapping("registerPage")
    public String registerPage() {
        return "register-page";
    }
}
