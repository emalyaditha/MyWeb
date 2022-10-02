package com.example.GoCheeta.controller;
import com.example.GoCheeta.model.Booking;
import com.example.GoCheeta.model.Users;
import com.example.GoCheeta.model.Vehicle;
import com.example.GoCheeta.repository.BookingRepository;
import com.example.GoCheeta.service.BookingService;
import com.example.GoCheeta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private UserService service1;

    @Autowired
    private BookingService serviceB;

    @Autowired
    private BookingRepository bookRepo;

    @GetMapping("")
    public String showHomepage() {

        return "index";
    }



    @GetMapping("/login")
    public String login() {

        return "login";
    }

    //    @PostMapping("/login")
//    public String login(@ModelAttribute("customer") Customer user, RedirectAttributes ra, Model model) {
//
//        Customer oauthUser = service.log(user.getEmail(),user.getPassword());
//
//        String email = user.getEmail();
//
//        System.out.println(oauthUser);
//        if(Objects.nonNull(oauthUser))
//        {
//            ra.addFlashAttribute("message", "(" +email+  ")");
//
//            return "redirect:/customerHome";
//        } else {
//            ra.addFlashAttribute("message", "Please provide the corret email and password");
//
//
//            return "redirect:/login";
//        }
//    }
    @GetMapping("/customerHome")
    public String customerHome(Model model) {

        return "customerHome";
    }


    @GetMapping("/customerBooking")
    public String showCustomerBooking(Model model) {
        List<Booking> listBooking = serviceB.listAllB();
        model.addAttribute("listBooking", listBooking);
        return "customerBooking";
    }

    @GetMapping("/customerAddBooking")
    public String booking(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("pageTitle", "Add Booking");
        return "customerAddBooking";
    }

    @GetMapping("/driverViewBooking")
    public String showDriverBooking(Model model) {
        List<Booking> listBooking = serviceB.listAllB();
        model.addAttribute("listBooking", listBooking);
        return "driverViewBooking";
    }

    @PostMapping("/booking/add")
    public String saveBooking(Booking booking, RedirectAttributes rs) {
        serviceB.save(booking);
        rs.addFlashAttribute("message", "The Booking has been Successfully Added");
        return "redirect:/customerBooking";
    }

    @GetMapping("/Booking/delete1/{id}")
    public String deleteBooking1(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            serviceB.delete(id);
            rs.addFlashAttribute("message", "The Booking ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/customerBooking";
    }
}
