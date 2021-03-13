package pimba.domain.vehicle;

import javax.validation.constraints.NotNull;

/**
 * Created by paulo on 28/04/17.
 */
public class VehicleRegisterRequest {
    @NotNull
    private String name;
    @NotNull
    private String motor;
    @NotNull
    private String brand;
    @NotNull
    private Integer year;
    @NotNull
    private String licensePlate;

    public VehicleRegisterRequest() {
        super();
    }

    public VehicleRegisterRequest(String name, String motor, String brand, Integer year, String licensePlate) {
        super();
        this.name = name;
        this.motor = motor;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
