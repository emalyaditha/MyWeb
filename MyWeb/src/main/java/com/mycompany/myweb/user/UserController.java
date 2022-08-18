package com.mycompany.myweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUser = service.listAll();
        model.addAttribute("listUser", listUser);

        return "users";
    }
//    @GetMapping("/login")
//    public String login(Model model) {
//
////        List<User> listUser = service.listAll();
////        model.addAttribute("listUser", listUser);
//
//        return "loginuser";
//    }
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

    public String showRegForm(Model model , RedirectAttributes rs) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Sing Up");
        return "regi_form";
    }
    @PostMapping("/users/add")
    public String saveUser(User user, RedirectAttributes rs) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        rs.addFlashAttribute("message", "The User has been saved successfully");
        return "redirect:/users";
    }
    @PostMapping("/users/reg")
    public String regiUser(User user, RedirectAttributes rs) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        rs.addFlashAttribute("message", "You have been Registered Succesfully");
        return "redirect:/index";
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
