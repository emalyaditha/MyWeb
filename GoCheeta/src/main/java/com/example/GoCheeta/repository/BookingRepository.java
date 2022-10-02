package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository  extends CrudRepository<Booking, Integer> {

    public long countById(Integer id);

    @Query(value = "SELECT * FROM booking WHERE branch LIKE %?1%", nativeQuery = true)
    List<Booking> searchDriver(String keyword);
}
