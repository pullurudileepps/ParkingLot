package Repository;

import Model.Vehicle;
import Model.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {
    private Map<Integer, Vehicle> map;

    public VehicleRepository() {
        this.map = new HashMap<>();
    }

    public Map<Integer, Vehicle> getVehicles() {
        return map;
    }

    public void setVehicles(Map<Integer, Vehicle> map) {
        this.map = map;
    }

    private static int id = 1;

    public Vehicle createIfNotExists(String vehicleNumber, VehicleType type) {
        for (Map.Entry<Integer, Vehicle> entry : map.entrySet()) {
            Vehicle value = entry.getValue();
            if (value.getVehicleNumber().equals(vehicleNumber)) {
                return value;
            }
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(type);
        map.put(id++, vehicle);
        return vehicle;
    }
}
