package com.example.GoCheeta.model;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 45, name = "vehicle_nameee")
    private String vehicleName;

    @Column(length = 45, name = "vehicle_model")
    private String  vehicleModel;

    @Column(length = 45, name = "number_plate")
    private String numberPlate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleNameee) {
        this.vehicleName = vehicleNameee;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Vehicle(Integer id, String vehicleName, String vehicleModel, String numberPlate) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.vehicleModel = vehicleModel;
        this.numberPlate = numberPlate;
    }

    public Vehicle() {
    }
}
