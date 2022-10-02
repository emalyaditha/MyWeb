package com.example.GoCheeta.controller;

import com.example.GoCheeta.model.Booking;
import com.example.GoCheeta.model.Users;
import com.example.GoCheeta.model.Vehicle;
import com.example.GoCheeta.repository.UserRepository;
import com.example.GoCheeta.repository.VehicleRepository;
import com.example.GoCheeta.service.AdminService;
import com.example.GoCheeta.service.BookingService;
import com.example.GoCheeta.service.UserService;
import com.example.GoCheeta.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class AdminController {

    @Autowired
    private AdminService service;
    @Autowired
    private UserService service1;
    @Autowired
    private BookingService serviceB;
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserRepository UserRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @GetMapping("/adminLogin")
    public String showNewForm() {

        return "adminLogin";
    }

    @GetMapping("/adminHome")
    public String showAHomeForm() {

        return "adminHome";
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String admin(ModelMap model, @RequestParam String email, @RequestParam String password, RedirectAttributes ra) {
        if (email.equals("admin@gmail.com") && password.equals("11111")) {
            ra.addFlashAttribute("message", "You have successfully logged in");
            return "redirect:/adminHome";
        }
            ra.addFlashAttribute("message", "Please provide the corret email and password");
            return "redirect:/adminLogin";
    }

    @GetMapping("/adminManageUsers")
    public String showCustomerList(Model model) {
        List<Users> listUser = service1.listAll();
        model.addAttribute("listUser", listUser);

        return "adminManageUsers";
    }
    @GetMapping("/adminAddUsers")
    public String AddUser(Model model) {
        model.addAttribute("users", new Users());
        model.addAttribute("pageTitle", "Add User");
        return "adminAddUsers";
    }

    @PostMapping("/user/add")
    public String saveUser(Users users, RedirectAttributes rs) {
        service1.save(users);
        rs.addFlashAttribute("message", "The User has been Successfully Added");
        return "redirect:/adminManageUsers";
    }

    @GetMapping("/customerRegister")
    public String showNewForm(@ModelAttribute("user") Users users, Model model) {
        model.addAttribute("users", new Users());
        model.addAttribute("pageTitle", "Register Form.");
        return "customerRegister";
    }


    @PostMapping("/user/add1")
    public String saveUser1(Users users, RedirectAttributes rs) {
        service1.save(users);
        rs.addFlashAttribute("message", "The User has been Successfully Added");
        return "redirect:/login";
    }


    @GetMapping("/User/edit/{id}")
    public String customerEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rs) {
        try {
            Users users = service1.get(id);
            model.addAttribute("users", users);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "adminUpdateUsers";
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", "The Customer has been updated successfully");

        }
        return "adminManageUsers";
    }

    @GetMapping("/User/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            service1.delete(id);
            rs.addFlashAttribute("message", "The Customer ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/adminManageUsers";
    }

    @PostMapping("/adminUpdateUsers")
    public String updateCustomer(Users users, RedirectAttributes ra) {
        service.save(users);
        ra.addFlashAttribute("message", "The Customer has been updated successfully");
        return "redirect:/adminManageUsers";
    }



    @GetMapping("/vehicles/edit/{id}")
    public String vehicleEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Vehicle vehicle = vehicleService.get(id);
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("pageTitle", "Edit Vehicle (ID: " + id + ")");
            return "adminUpdateVehicle";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The Vehicles has been updated successfully");
            return "redirect:/adminManageVehicle";
        }
    }
    @GetMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            vehicleService.delete(id);
            rs.addFlashAttribute("message", "The Vehicles ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/adminManageVehicle";
    }

    @GetMapping("/adminAddVehicles")
    public String ShowVehiclePage(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("pageTitle", "Add Vehicles");
        return "adminAddVehicle";
    }
    @PostMapping("/vehicle/add")
    public String saveVehicles(Vehicle vehicle, RedirectAttributes rs) {
        vehicleService.save(vehicle);
        rs.addFlashAttribute("message", "The Vehicles has been Successfully Added");
        return "redirect:/adminManageVehicle";
    }


    @GetMapping("/adminManageVehicle")
    public String showVehicleList(Model model) {
        List<Vehicle> listVehicle = vehicleService.listAllV();
        model.addAttribute("listVehicle", listVehicle);

        return "adminManageVehicle";
    }

    @PostMapping("/adminUpdateVehicle")
    public String updateVehicle(Vehicle vehicle, RedirectAttributes ra) {
        vehicleService.save(vehicle);
        ra.addFlashAttribute("message", "The Vehicle has been updated successfully");
        return "redirect:/adminManageVehicle";
    }
    @GetMapping("/adminManageBooking")
    public String adminManageBooking(Model model) {
        List<Booking> listBooking = serviceB.listAllB();
        model.addAttribute("listBooking", listBooking);
        return "adminManageBooking";
    }
    @PostMapping("/adminUpdateBooking")
    public String updateBooking(Booking booking, RedirectAttributes ra) {
        serviceB.save(booking);
        ra.addFlashAttribute("message", "The Booking has been updated successfully");
        return "redirect:/adminManageBooking";
    }

    @GetMapping("/booking/edit/{id}")
    public String BookingEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Booking booking = serviceB.get(id);
            model.addAttribute("booking", booking);
            model.addAttribute("pageTitle", "Edit Booking (ID: " + id + ")");
            return "/adminUpdateBooking";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The Booking has been updated successfully");
        }
        return "redirect:/adminManageBooking";
    }

    @GetMapping("/booking/edit1/{id}")
    public String BookingEditForm1(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Booking booking = serviceB.get(id);
            model.addAttribute("booking", booking);
            model.addAttribute("pageTitle", "Edit Booking (ID: " + id + ")");
            return "driverAcceptBooking";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The Booking has been updated successfully");
        }
        return "redirect:/driverViewBooking";
    }
    @PostMapping("/driverAcceptBooking")
    public String updateBooking1(Booking booking, RedirectAttributes ra) {
        serviceB.save(booking);
        ra.addFlashAttribute("message", "The Booking has been Accepted successfully");
        return "redirect:/driverViewBooking";
    }
    @GetMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable("id") Integer id, RedirectAttributes rs) {
        try {
            serviceB.delete(id);
            rs.addFlashAttribute("message", "The Booking ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            rs.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/adminManageBooking";
    }

    @GetMapping("/driverHome")
    public String driverHome(Model model) {

        return "driverHome";
    }

    @GetMapping("/driverViewHires")
    public String driverHires(Model model) {
        List<Booking> listBooking = serviceB.listAllB();
        model.addAttribute("listBooking", listBooking);
        return "driverViewHires";
    }
    @GetMapping("/findBooking")
    public String findHire(Model model, @Param("keyword") String keyword) {
            List<Booking> booking = serviceB.lisAllDriverBookings(keyword);
            model.addAttribute("Booking", booking);
            model.addAttribute("keyword", keyword);
            return "driverViewHires";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";
    }

}
