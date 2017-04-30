package pimba.domain.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.domain.common.Common;
import pimba.exceptions.InvalidVehicleException;
import pimba.login.model.user.User;
import pimba.login.spring.service.SecurityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 28/04/17.
 */
@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    SecurityService securityService;

    public List<Vehicle> getVehicles() {
        User user = securityService.getCurrentUser().get();
        Common common = user.getCommon();
        /*if (common.getVehicles().isEmpty()) {
            return null;
        }*/
        List<Vehicle> vehicles = new ArrayList<>(common.getVehicles());
        return vehicles;
    }

    public Vehicle registerVehicle(String name, String motor, String brand, Integer year, String licensePlate) {
        User user = securityService.getCurrentUser().get();
        Common common = user.getCommon();
        Vehicle vehicle = new Vehicle(name, motor, brand, year, licensePlate, common);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle editVehicleName(String name, Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new InvalidVehicleException("Vehicle not found"));
        vehicle.setName(name);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle editVehicleMotor(String motor, Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new InvalidVehicleException("Vehicle not found"));
        vehicle.setMotor(motor);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle editVehicleBrand(String brand, Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new InvalidVehicleException("Vehicle not found"));
        vehicle.setBrand(brand);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle editVehicleYear(Integer year, Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new InvalidVehicleException("Vehicle not found"));
        vehicle.setYear(year);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle editVehicleLicensePlate(String licensePlate, Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new InvalidVehicleException("Vehicle not found"));
        vehicle.setLicensePlate(licensePlate);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

}
