package Model;

public enum VehicleType {
    BIKE,
    CAR,
    TRUCK,
    EV_CAR;

    public static VehicleType getTypeFromStr(String type) {
        for (VehicleType value : VehicleType.values()) {
            if(type.equalsIgnoreCase(value.toString())){
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported vehicle type");
    }
}
