package Repository;

import Model.Invoice;

import java.util.HashMap;
import java.util.Map;

public class InvoiceRepository {
    private Map<Integer, Invoice> map;

    public InvoiceRepository() {
        this.map = new HashMap<>();
    }

    public Map<Integer, Invoice> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Invoice> map) {
        this.map = map;
    }

    private static int id = 1;

    public Invoice insertInvoice(Invoice invoice) {
        invoice.setId(id);
        map.put(id++, invoice);
        return invoice;
    }
}
