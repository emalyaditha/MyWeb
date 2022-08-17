package com.mycompany.myweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUser = service.listAll();
        model.addAttribute("listUser", listUser);

        return "users";
    }
    @GetMapping("/login")
    public String login(Model model) {

//        List<User> listUser = service.listAll();
//        model.addAttribute("listUser", listUser);

        return "login";
    }
    @GetMapping("/index")
    public String index(Model model) {
//        List<User> listUser = service.listAll();
//        model.addAttribute("listUser", listUser);

        return "index";
    }

    @GetMapping("users/new")

    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new User");
        return "user_form";
    }
    @GetMapping("/users/reg")

    public String showRegForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Register");
        return "user_form";
    }
    @PostMapping("/users/add")
    public String saveUser(User user, RedirectAttributes rs) {
        service.save(user);
        rs.addFlashAttribute("message", "The User has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rs) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", "The User has been updated successfully");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            service.delete(id);
            rs.addFlashAttribute("message", "The User ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/users";
    }

}
