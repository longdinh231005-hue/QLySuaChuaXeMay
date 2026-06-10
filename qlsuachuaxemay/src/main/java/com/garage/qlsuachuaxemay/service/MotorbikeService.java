package com.garage.qlsuachuaxemay.service;

import com.garage.qlsuachuaxemay.entity.Motorbike;
import java.util.List;

public interface MotorbikeService {
    List<Motorbike> getAllActiveMotorbikes();
    List<Motorbike> searchMotorbikes(String keyword);
    Motorbike getMotorbikeByLicensePlate(String licensePlate);
    Motorbike saveMotorbike(Motorbike motorbike);
    Motorbike updateMotorbike(String licensePlate, Motorbike motorbikeDetails);
    void deleteMotorbike(String licensePlate);
    boolean existsByLicensePlate(String licensePlate);
}
