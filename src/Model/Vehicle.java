package Model;

public class Vehicle extends BaseModel {
    private String VehicleNumber;
    private VehicleType VehicleType;

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public Model.VehicleType getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(Model.VehicleType vehicleType) {
        VehicleType = vehicleType;
    }
}
