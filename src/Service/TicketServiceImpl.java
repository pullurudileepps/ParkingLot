package Service;

import Exceptions.InValidRequest;
import Model.*;
import Repository.GateRepository;
import Repository.ParkingLotRepository;
import Repository.TicketRespository;
import Repository.VehicleRepository;
import Stretagy.AssignSpotStretagy;

import java.util.Date;

public class TicketServiceImpl implements TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private AssignSpotStretagy assignSpotStretagy;
    private TicketRespository ticketRespository;

    public TicketServiceImpl(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, AssignSpotStretagy assignSpotStretagy, TicketRespository ticketRespository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.assignSpotStretagy = assignSpotStretagy;
        this.ticketRespository = ticketRespository;
    }

    @Override
    public Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType) {
        Gate gate = gateRepository.getGateById(gateId);
        VehicleType type = VehicleType.getTypeFromStr(vehicleType);
        Vehicle vehicle = vehicleRepository.createIfNotExists(vehicleNumber, type);
        Parkinglot parkinglot = parkingLotRepository.getParkingLotByGateId(gateId);
        if(parkinglot == null) {
            throw new InValidRequest("Invalid gate id");
        }
        Spot spot = assignSpotStretagy.assignSpot(type, parkinglot);
        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setAssignedSpot(spot);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        return ticketRespository.insertTicket(ticket);
    }
}
