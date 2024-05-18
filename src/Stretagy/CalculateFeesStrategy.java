package Stretagy;

import Model.VehicleType;

import java.util.Date;

public interface CalculateFeesStrategy {
    double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType);
}
