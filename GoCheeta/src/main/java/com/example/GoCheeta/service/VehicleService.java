package com.example.GoCheeta.service;

import com.example.GoCheeta.controller.UserNotFoundException;
import com.example.GoCheeta.model.Vehicle;
import com.example.GoCheeta.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired private VehicleRepository vehicleRepo;

    public List<Vehicle> listAllV() {
        return (List<Vehicle>) vehicleRepo.findAll();
    }

    public void save (Vehicle vehicle) {
        vehicleRepo.save(vehicle);
    }

    public Vehicle get(Integer id) throws UserNotFoundException {
        Optional<Vehicle> result = vehicleRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any Vehicle with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = vehicleRepo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any Vehicle with ID:" + id);
        }
        vehicleRepo.deleteById(id);
    }
}
