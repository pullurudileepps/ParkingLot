package Model;

import java.util.Date;

public class Slab extends BaseModel {
    private int startHour;
    private int endHour;
    private VehicleType vehicleType;
    private double pricePerHour;

    public Slab(int id, int startHour, int endHour, VehicleType vehicleType, double pricePerHour) {
        setId(id);
        this.startHour = startHour;
        this.endHour = endHour;
        this.vehicleType = vehicleType;
        this.pricePerHour = pricePerHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
