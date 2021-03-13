package pimba.domain.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pimba.login.spring.service.CurrentUserVerication;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by paulo on 28/04/17.
 */
@RestController
@RequestMapping("${vehicle.base}")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CurrentUserVerication currentUserVerication;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "${vehicle.vehicles}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        if (currentUserVerication.isCurrentUserCommon()) {
            List<Vehicle> vehicles = vehicleService.getVehicles();
            return ResponseEntity.ok(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "${vehicle.register}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody @Valid VehicleRegisterRequest request) {
        if (currentUserVerication.isCurrentUserCommon()) {
            Vehicle vehicle = vehicleService.registerVehicle(request.getName(), request.getMotor(), request.getBrand(), request.getYear(), request.getLicensePlate());
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{vehicleId}/${vehicle.edit.base}/${vehicle.edit.name}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehicle> editName(@PathVariable("vehicleId") Integer vehicleId, @RequestParam String name) {
        if (name.isEmpty() || vehicleId == null) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(vehicleService.editVehicleName(name, vehicleId));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{vehicleId}/${vehicle.edit.base}/${vehicle.edit.motor}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehicle> editMotor(@PathVariable("vehicleId") Integer vehicleId, @RequestParam String motor) {
        if (motor.isEmpty() || vehicleId == null) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(vehicleService.editVehicleMotor(motor, vehicleId));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{vehicleId}/${vehicle.edit.base}/${vehicle.edit.brand}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehicle> editBrand(@PathVariable("vehicleId") Integer vehicleId, @RequestParam String brand) {
        if (brand.isEmpty() || vehicleId == null) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(vehicleService.editVehicleBrand(brand, vehicleId));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{vehicleId}/${vehicle.edit.base}/${vehicle.edit.year}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehicle> editYear(@PathVariable("vehicleId") Integer vehicleId, @RequestParam Integer year) {
        if (year == null || vehicleId == null) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(vehicleService.editVehicleYear(year, vehicleId));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{vehicleId}/${vehicle.edit.base}/${vehicle.edit.licensePlate}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehicle> editLicensePlate(@PathVariable("vehicleId") Integer vehicleId, @RequestParam String licensePlate) {
        if (licensePlate.isEmpty() || vehicleId == null) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(vehicleService.editVehicleLicensePlate(licensePlate, vehicleId));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }


}
