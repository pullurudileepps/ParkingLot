package Repository;

import Model.Gate;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {
    private Map<Integer, Gate> map;

    public GateRepository(Map<Integer, Gate> map) {
        this.map = map;
    }

    public Map<Integer, Gate> getGates() {
        return map;
    }

    public void setGates(Map<Integer, Gate> map) {
        this.map = map;
    }

    public Gate getGateById(int gateId) {
        return map.get(gateId);
    }
}
