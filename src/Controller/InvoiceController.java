package Controller;

import Dto.*;
import Exceptions.InValidRequest;
import Model.Invoice;
import Service.InvoiceService;

public class InvoiceController {
    InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public InvoiceGenerationResp GenerateTicket(InvoiceGenerationReq req) {
        InvoiceGenerationResp resp = new InvoiceGenerationResp();
        Response response = new Response();
        try {
            if (req.getGateId() < 0) {
                throw new InValidRequest("invalid gate id");
            }
            if (req.getTicketId() < 0) {
                throw new InValidRequest("invalid ticket id");
            }
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        try {
            Invoice invoice = invoiceService.generateInvoice(req.getTicketId(), req.getGateId());
            response.setStatus(ResponseStatus.SUCCESS);
            resp.setInvoice(invoice);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        resp.setResponse(response);
        return resp;
    }
}
