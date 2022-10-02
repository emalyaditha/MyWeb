package com.example.GoCheeta.model;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String customer;

    @Column(length = 255, nullable = false)
    private String destination;

    @Column(length = 45, nullable = false)
    private String driver;

    @Column(length = 100, nullable = false, name = "starting_location")
    private String startingLocation;

    @Column(length = 100, nullable = false, name = "vehicle_branch")
    private String vehicleBranch;

    @Column(length = 45, nullable = false, name = "vehicle_Name")
    private String vehicleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getVehicleBranch() {
        return vehicleBranch;
    }

    public void setVehicleBranch(String vehicleBranch) {
        this.vehicleBranch = vehicleBranch;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Booking(Integer id, String customer, String destination, String driver, String startingLocation, String vehicleBranch, String vehicleName) {
        this.id = id;
        this.customer = customer;
        this.destination = destination;
        this.driver = driver;
        this.startingLocation = startingLocation;
        this.vehicleBranch = vehicleBranch;
        this.vehicleName = vehicleName;
    }

    public Booking() {
    }
}
