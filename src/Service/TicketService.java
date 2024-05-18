package Service;

import Model.Ticket;
import Model.VehicleType;

public interface TicketService {
    Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType);
}
