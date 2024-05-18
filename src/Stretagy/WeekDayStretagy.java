package Stretagy;

import Model.VehicleType;
import Util.DateTimeUtils;

import java.util.Date;

public class WeekDayStretagy implements CalculateFeesStrategy {
    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        return DateTimeUtils.calcHours(entryTime, exitTime) * 10;
    }
}
