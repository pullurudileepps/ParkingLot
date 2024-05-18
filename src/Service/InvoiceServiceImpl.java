package Service;

import Exceptions.InvalidGateException;
import Exceptions.InvalidTicketException;
import Model.*;
import Repository.GateRepository;
import Repository.InvoiceRepository;
import Repository.TicketRespository;
import Stretagy.CalculateFeesStrategy;
import Stretagy.CalculateFeesStrategyFactory;
import java.util.Arrays;
import java.util.Date;

public class InvoiceServiceImpl implements InvoiceService{
    private TicketRespository ticketRespository;
    private GateRepository gateRepository;
    private CalculateFeesStrategyFactory factory;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketRespository ticketRespository, GateRepository gateRepository, CalculateFeesStrategyFactory factory, InvoiceRepository invoiceRepository) {
        this.ticketRespository = ticketRespository;
        this.gateRepository = gateRepository;
        this.factory = factory;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice generateInvoice(int ticketId, int gateId) {
        Ticket ticket = ticketRespository.getTicketById(ticketId);
        if(ticket == null){
            throw new InvalidTicketException("Ticket is not present in DB");
        }
        Gate gate = gateRepository.getGateById(gateId);
        if(gate == null) {
            throw new InvalidTicketException("Gate is not present in DB");
        }
        if(gate.getGateType().equals(GateType.ENTRY)){
            throw new InvalidGateException("Invoice cannot be created at entry gate");
        }
        Date entryTime = ticket.getEntryTime();
        Date exitTime = new Date();
        CalculateFeesStrategy calculateFeesStrategy = factory.getCalculateFeesStrategy(exitTime);
        double totalamount = calculateFeesStrategy.calculateFees(entryTime, exitTime, ticket.getVehicle().getVehicleType());

        InvoiceDetail invoiceDetail = new InvoiceDetail(totalamount,"Parking fee");
//        invoiceDetail.setName("Parking fee");
//        invoiceDetail.setPrice(totalamount);

        Invoice invoice = new Invoice();
        invoice.setAmount(totalamount);
        invoice.setExitTime(exitTime);
        invoice.setTicket(ticket);
        invoice.setDetails(Arrays.asList(invoiceDetail));
        return invoiceRepository.insertInvoice(invoice);
    }
}
