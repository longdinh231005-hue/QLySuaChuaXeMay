package com.garage.qlsuachuaxemay.service.impl;

import com.garage.qlsuachuaxemay.entity.Motorbike;
import com.garage.qlsuachuaxemay.repository.MotorbikeRepository;
import com.garage.qlsuachuaxemay.service.MotorbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MotorbikeServiceImpl implements MotorbikeService {

    @Autowired
    private MotorbikeRepository motorbikeRepository;

    @Override
    public List<Motorbike> getAllActiveMotorbikes() {
        return motorbikeRepository.findByStatusTrue();
    }

    @Override
    public List<Motorbike> searchMotorbikes(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllActiveMotorbikes();
        }
        return motorbikeRepository.searchActiveMotorbikes(keyword.trim());
    }

    @Override
    public Motorbike getMotorbikeByLicensePlate(String licensePlate) {
        return motorbikeRepository.findByLicensePlateAndStatusTrue(licensePlate)
                .orElseThrow(() -> new IllegalArgumentException("Xe máy không tồn tại hoặc đã bị ngừng hoạt động."));
    }

    @Override
    public Motorbike saveMotorbike(Motorbike motorbike) {
        if (motorbikeRepository.existsByLicensePlate(motorbike.getLicensePlate())) {
            throw new IllegalArgumentException("Biển số xe này đã tồn tại trong hệ thống.");
        }
        motorbike.setStatus(true);
        return motorbikeRepository.save(motorbike);
    }

    @Override
    public Motorbike updateMotorbike(String licensePlate, Motorbike motorbikeDetails) {
        Motorbike existing = getMotorbikeByLicensePlate(licensePlate);
        
        existing.setBrand(motorbikeDetails.getBrand());
        existing.setModel(motorbikeDetails.getModel());
        existing.setFrameNumber(motorbikeDetails.getFrameNumber());
        existing.setEngineNumber(motorbikeDetails.getEngineNumber());
        existing.setCustomer(motorbikeDetails.getCustomer());
        return motorbikeRepository.save(existing);
    }

    @Override
    public void deleteMotorbike(String licensePlate) {
        Motorbike existing = getMotorbikeByLicensePlate(licensePlate);
        existing.setStatus(false); // Soft delete
        motorbikeRepository.save(existing);
    }

    @Override
    public boolean existsByLicensePlate(String licensePlate) {
        return motorbikeRepository.existsByLicensePlate(licensePlate);
    }
}
