package hu.unideb.typeteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TypeTeamController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/notes")
    public String showNotes() {
        return "notes";
    }

}
