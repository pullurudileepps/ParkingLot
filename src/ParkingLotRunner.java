import Controller.InvoiceController;
import Controller.TicketController;
import Dto.InvoiceGenerationReq;
import Dto.InvoiceGenerationResp;
import Dto.TicketGenerationReq;
import Dto.TicketGenerationResp;
import Model.*;
import Repository.*;
import Service.InvoiceService;
import Service.InvoiceServiceImpl;
import Service.TicketService;
import Service.TicketServiceImpl;
import Stretagy.AssignSpotStretagy;
import Stretagy.AssignSpotStretagyImpl;
import Stretagy.CalculateFeesStrategyFactory;

import java.util.*;

public class ParkingLotRunner {
    public static void main(String[] args) {
        Gate g1 = new Gate(GateType.ENTRY, new Operations("xyz", "xyz@gmail.com"), "1A");
        g1.setId(1);

        Gate g2 = new Gate(GateType.EXIT, new Operations("abc", "abc@gmail.com"), "4Z");
        g2.setId(2);
        List<Spot> spots = Arrays.asList(new Spot("1A", VehicleType.CAR, SpotStatus.UNOCCUPIED));

        List<Section> sections = new ArrayList<>();
        Section section = new Section();
        section.setName("AA");
        section.setId(1);
        section.setSpots(spots);
        sections.add(section);

        List<Floor> floors = new ArrayList<>();
        Floor floor = new Floor();
        floor.setFloorNumber(1);
        floor.setFloorStatus(FloorStatus.OPERATIONAL);
        floor.setSections(sections);
        floor.setId(1);
        floors.add(floor);

        List<Gate> gateslIST = Arrays.asList(g1, g2);

        Parkinglot parkingLot = new Parkinglot();
        parkingLot.setFloors(floors);
        parkingLot.setGates(gateslIST);
        parkingLot.setId(1);

        Slab slab1 = new Slab(1, 0, 2, VehicleType.CAR, 10);
        Slab slab2 = new Slab(2, 2, 4, VehicleType.CAR, 20);
        Slab slab3 = new Slab(3, 4, 8, VehicleType.CAR, 25);
        Slab slab4 = new Slab(4, 8, -1, VehicleType.CAR, 40);

        Map<Integer, Gate> gates = new HashMap<Integer, Gate>() {{
            put(1, g1);
            put(2, g2);
        }};

        Map<Integer, Parkinglot> parkingLotMap = new HashMap<Integer, Parkinglot>() {{
            put(1, parkingLot);
        }};

        Map<Integer, Slab> slabMap = new HashMap<Integer, Slab>() {{
            put(1, slab1);
            put(2, slab2);
            put(3, slab3);
            put(4, slab4);
        }};
        SlabRepository slabRepository = new SlabRepository(slabMap);
        CalculateFeesStrategyFactory factory = new CalculateFeesStrategyFactory(slabRepository);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotMap);
        GateRepository gateRepository = new GateRepository(gates);
        TicketRespository ticketRespository = new TicketRespository();
        InvoiceRepository invoiceRepository = new InvoiceRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        AssignSpotStretagy assignSpotStrategy = new AssignSpotStretagyImpl();
        TicketService ticketService = new TicketServiceImpl(gateRepository, vehicleRepository,parkingLotRepository,assignSpotStrategy,ticketRespository);

        InvoiceService invoiceService = new InvoiceServiceImpl(ticketRespository, gateRepository, factory, invoiceRepository);

        TicketController ticketController = new TicketController(ticketService);
        InvoiceController invoiceController = new InvoiceController(invoiceService);


        TicketGenerationReq requestDto = new TicketGenerationReq();
        requestDto.setGateId(1);
        requestDto.setVehicleType(VehicleType.CAR.toString());
        requestDto.setVehicleNumber("KA 05 1234");

        TicketGenerationResp responseDto = ticketController.GenerateTicket(requestDto);
        System.out.println(responseDto);
        int ticketId = responseDto.getTicket().getId();

        requestDto.setVehicleNumber("KA 05 6789");
        responseDto = ticketController.GenerateTicket(requestDto);
        System.out.println(responseDto);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Exception while sleeping");
        }
        InvoiceGenerationReq invoiceReqDto = new InvoiceGenerationReq();
        invoiceReqDto.setGateId(1);
        invoiceReqDto.setTicketId(ticketId);
        invoiceReqDto.setGateId(g2.getId());

        InvoiceGenerationResp invoiceGenerationResp = invoiceController.GenerateTicket(invoiceReqDto);
        System.out.println(invoiceGenerationResp);

    }
}