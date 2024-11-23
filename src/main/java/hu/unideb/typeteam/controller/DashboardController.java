package hu.unideb.typeteam.controller;

import hu.unideb.typeteam.entity.User;
import hu.unideb.typeteam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class DashboardController{
    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("users", userList);
        return "dashboard";
    }

    @PostMapping("/dashboard/delete/{userId}")
    public String delete(@PathVariable("userId") String id) {
        userService.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/edit/{userId}")
    public String edit(@PathVariable("userId") String id, Model model) {
        User user = userService.findUserByUserId(id);
        model.addAttribute("user", user);
        return "/user-edit";
    }
}
