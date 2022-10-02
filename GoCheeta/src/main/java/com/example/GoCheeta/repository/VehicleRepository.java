package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    public long countById(Integer id);
}
