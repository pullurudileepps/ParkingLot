package Stretagy;

import Exceptions.NoSpotAvailableException;
import Model.*;

public class AssignSpotStretagyImpl implements AssignSpotStretagy {
    @Override
    public Spot assignSpot(VehicleType vehicleType, Parkinglot parkingLot) throws NoSpotAvailableException {
        for (Floor floor : parkingLot.getFloors()) {
            if(floor.getFloorStatus().equals(FloorStatus.OPERATIONAL)) {
                for (Section section : floor.getSections()) {
                    for (Spot spot : section.getSpots()) {
                        if (spot.getVehicleType().equals(vehicleType) && spot.getSpotStatus().equals(SpotStatus.UNOCCUPIED)) {
                            spot.setSpotStatus(SpotStatus.OCCUPIED);
                            return spot;
                        }
                    }
                }
            }
        }
        throw new NoSpotAvailableException("No spots available for " + vehicleType);
    }
}