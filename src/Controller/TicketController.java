package Controller;

import Dto.Response;
import Dto.ResponseStatus;
import Dto.TicketGenerationReq;
import Dto.TicketGenerationResp;
import Exceptions.InValidRequest;
import Model.Ticket;
import Service.TicketService;

public class TicketController {
    TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    public TicketGenerationResp GenerateTicket(TicketGenerationReq request) {
        TicketGenerationResp resp = new TicketGenerationResp();
        Response response = new Response();
        try {
            if (request.getGateId() < 0) {
                throw new InValidRequest("Invalid gateId");
            }
            if (request.getVehicleNumber() == null || request.getVehicleNumber().isEmpty()) {
                throw new InValidRequest("Invalid vehicleNumber");
            }
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        try {
            Ticket ticket = ticketService.generateTicket(request.getGateId(), request.getVehicleNumber(), request.getVehicleType());
            response.setStatus(ResponseStatus.SUCCESS);
            resp.setTicket(ticket);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        resp.setResponse(response);
        return resp;
    }
}
