package Service;

import Model.Invoice;

public interface InvoiceService {
    Invoice generateInvoice(int ticketId, int gateId);
}
