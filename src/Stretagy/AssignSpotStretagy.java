package Stretagy;

import Exceptions.NoSpotAvailableException;
import Model.Parkinglot;
import Model.Spot;
import Model.VehicleType;

public interface AssignSpotStretagy {
    public Spot assignSpot(VehicleType vehicleType, Parkinglot parkingLot) throws NoSpotAvailableException;
}
