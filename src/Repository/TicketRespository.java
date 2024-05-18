package Repository;

import Model.Ticket;
import java.util.HashMap;
import java.util.Map;

public class TicketRespository {

    private Map<Integer, Ticket> map;

    public TicketRespository(Map<Integer, Ticket> map) {
        this.map = map;
    }

    public TicketRespository() {
        this.map = new HashMap<>();
    }

    private static int id = 1;

    public Ticket insertTicket(Ticket ticket){
        ticket.setId(id);
        map.put(id++, ticket);
        return ticket;
    }
    public Ticket getTicketById(int ticketId){
        return map.get(ticketId);
    }
}
