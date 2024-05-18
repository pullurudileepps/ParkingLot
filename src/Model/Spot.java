package Model;

public class Spot extends BaseModel {
    private String spotName;
    private VehicleType vehicleType;
    private SpotStatus spotStatus;

    public Spot(String spotName, VehicleType vehicleType, SpotStatus spotStatus) {
        this.spotName = spotName;
        this.vehicleType = vehicleType;
        this.spotStatus = spotStatus;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(SpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }
}
