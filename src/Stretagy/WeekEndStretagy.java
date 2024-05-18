package Stretagy;

import Model.Slab;
import Model.VehicleType;
import Repository.SlabRepository;
import Util.DateTimeUtils;

import java.util.Date;
import java.util.List;

public class WeekEndStretagy implements CalculateFeesStrategy {
    SlabRepository slabRepository;

    public WeekEndStretagy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> slabs = slabRepository.getSlabsByVehicleType(vehicleType);
        int hours = DateTimeUtils.calcHours(entryTime, exitTime);
        double totalAmount = 0.0;
        for (Slab slab : slabs) {
            if (hours >= slab.getStartHour() && slab.getEndHour() != -1) {
                if (hours >= slab.getEndHour()) {
                    totalAmount += (slab.getEndHour() - slab.getStartHour()) * slab.getPricePerHour();
                } else {
                    totalAmount += (hours - slab.getStartHour()) * slab.getPricePerHour();
                }
            } else if (slab.getEndHour() == -1) {
                totalAmount += (hours - slab.getStartHour()) * slab.getPricePerHour();
            } else {
                break;
            }
        }
        return totalAmount;
    }
}
