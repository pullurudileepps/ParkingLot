package Repository;

import Model.Gate;
import Model.Parkinglot;

import java.util.Map;

public class ParkingLotRepository {
    private Map<Integer, Parkinglot> map;

    public ParkingLotRepository(Map<Integer, Parkinglot> map) {
        this.map = map;
    }

    public Map<Integer, Parkinglot> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Parkinglot> map) {
        this.map = map;
    }

    public Parkinglot getParkingLotByGateId(int gateId) {
        for (Map.Entry<Integer, Parkinglot> entry : map.entrySet()) {
            Parkinglot parkinglot = entry.getValue();
            for (Gate gate : parkinglot.getGates()) {
                if(gate.getId() == gateId){
                    return parkinglot;
                }
            }
        }
        return null;
    }
}
