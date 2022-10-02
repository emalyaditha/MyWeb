package com.example.GoCheeta.service;

import com.example.GoCheeta.controller.UserNotFoundException;
import com.example.GoCheeta.model.Booking;
import com.example.GoCheeta.model.Users;
import com.example.GoCheeta.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;


    public List<Booking> listAllB() { return (List<Booking>) repo.findAll(); }

  public void save(Booking booking) {
        repo.save(booking);}

    public List<Booking> lisAllDriverBookings(String keyword) {
        if (keyword != null) {
            return repo.searchDriver(keyword);
        }else {
            return listAllB();
        }
    }

    public Booking get(Integer id) throws UserNotFoundException {
        Optional<Booking> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any Booking with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any Booking with ID:" + id);
        }
        repo.deleteById(id);
    }
}
