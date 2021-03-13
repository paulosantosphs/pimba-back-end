package pimba.domain.vehicle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pimba.login.spring.service.CurrentUserVerication;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by paulo on 28/04/17.
 */
public class VehicleControllerTest {
    @InjectMocks
    private VehicleController controller;

    @Mock
    private CurrentUserVerication current;

    @Mock
    private VehicleService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void vehiclesTest() {
        Vehicle vehicle1 = new Vehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");
        Vehicle vehicle2 = new Vehicle("Jeta", "1.4 TSI", "Volkswagen", 2017, "ABC-1234");
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.getVehicles()).thenReturn(vehicles);
        Assert.assertEquals(ResponseEntity.ok(vehicles), controller.getVehicles());

        vehicles.clear();
        when(service.getVehicles()).thenReturn(vehicles);
        Assert.assertEquals(ResponseEntity.ok(new ArrayList<Vehicle>()), controller.getVehicles());
    }

    @Test
    public void registerVehicleTest() {
        VehicleRegisterRequest request = new VehicleRegisterRequest("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");
        Vehicle vehicle = new Vehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");

        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.registerVehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234")).thenReturn(vehicle);

        Assert.assertEquals(ResponseEntity.ok(vehicle), controller.registerVehicle(request));
    }

    @Test
    public void editVehicleNameTest() {
        Vehicle vehicle = new Vehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");

        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editVehicleName("Voyage", 1)).thenReturn(vehicle);

        Assert.assertEquals(ResponseEntity.ok(vehicle), controller.editName(1, "Voyage"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editName(1, ""));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editName(null, "Voyage"));
    }

    @Test
    public void editVehicleMotorTest() {
        Vehicle vehicle = new Vehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");

        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editVehicleMotor("1.6 MSI", 1)).thenReturn(vehicle);

        Assert.assertEquals(ResponseEntity.ok(vehicle), controller.editMotor(1, "1.6 MSI"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editMotor(1, ""));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editMotor(null, "1.6 MSI"));
    }

    @Test
    public void editVehicleBrandTest() {
        Vehicle vehicle = new Vehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");

        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editVehicleBrand("Volkswagen", 1)).thenReturn(vehicle);

        Assert.assertEquals(ResponseEntity.ok(vehicle), controller.editBrand(1, "Volkswagen"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editBrand(1, ""));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editBrand(null, "Volkswagen"));
    }

    @Test
    public void editVehicleYearTest() {
        Vehicle vehicle = new Vehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");

        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editVehicleYear(2017, 1)).thenReturn(vehicle);

        Assert.assertEquals(ResponseEntity.ok(vehicle), controller.editYear(1, 2017));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editYear(1, null));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editYear(null, 2017));
    }

    @Test
    public void editVehicleLicensePlateTest() {
        Vehicle vehicle = new Vehicle("Voyage", "1.6 MSI", "Volkswagen", 2017, "ABC-1234");

        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editVehicleLicensePlate("ABC-1234", 1)).thenReturn(vehicle);

        Assert.assertEquals(ResponseEntity.ok(vehicle), controller.editLicensePlate(1, "ABC-1234"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editLicensePlate(1, ""));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editLicensePlate(null, "ABC-1234"));
    }


}
