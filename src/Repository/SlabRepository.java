package Repository;

import Model.Slab;
import Model.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlabRepository {
    private Map<Integer, Slab> map;

    public SlabRepository(Map<Integer, Slab> map) {
        this.map = map;
    }

    public Map<Integer, Slab> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Slab> map) {
        this.map = map;
    }

    public List<Slab> getSlabsByVehicleType(VehicleType vehicleType) {
        List<Slab> slabs = new ArrayList<>();
        for (Map.Entry<Integer, Slab> entry : map.entrySet()) {
            Slab slab = entry.getValue();
            if (slab.getVehicleType().equals(vehicleType)) {
                slabs.add(slab);
            }
        }
        return slabs;
    }
}
