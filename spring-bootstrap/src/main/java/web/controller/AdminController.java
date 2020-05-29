package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.InitService;
import web.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private UserService service;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("panel")
    public String getUsersTable(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("userList", service.getAllUser());
        return "admin-panel";
    }

    @PostMapping("panel")
    public String addUser(@RequestParam String firstName, String lastName, String password, String email, int age, String role) {
        service.addUser(new User(firstName, lastName, password, email, age), role);
        return "redirect:/admin/panel";
    }

    @PostMapping("delete")
    public String deleteUser(User user) {
        service.deleteUser(user);
        return "redirect:/admin/panel";
    }

    @PostMapping("update")
    public String updateUser(@RequestParam Long id, String firstName, String lastName, String password, String email, int age, String role) {
        password = service.ifPasswordNull(id, password);
        service.updateUser(new User(id, firstName, lastName, password, email, age), role);
        return "redirect:/admin/panel";
    }

    @GetMapping("add")
    public String newUser(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "/add-user";
    }
}
