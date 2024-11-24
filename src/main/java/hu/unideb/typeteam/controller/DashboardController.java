package hu.unideb.typeteam.controller;

import hu.unideb.typeteam.entity.Role;
import hu.unideb.typeteam.entity.User;
import hu.unideb.typeteam.repository.RoleRepository;
import hu.unideb.typeteam.service.RoleService;
import hu.unideb.typeteam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController{
    private final UserService userService;
    private final RoleService roleService;

    public DashboardController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
        List<Role> allRoles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", allRoles);
        return "edit-user";
    }

    @PostMapping("/dashboard/edit/{userId}")
    public String saveUserRoles(@PathVariable("userId") String id, @RequestParam("roles") List<Integer> roleIds, Model model) {
        User user = userService.findUserByUserId(id);
        List<Role> selectedRoles = roleService.findRolesByIds(roleIds);
        user.setRoles(selectedRoles);
        userService.saveUser(user);
        return "redirect:/dashboard";
    }
}
