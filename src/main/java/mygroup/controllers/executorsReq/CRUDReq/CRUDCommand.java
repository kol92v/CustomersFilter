package mygroup.controllers.executorsReq.CRUDReq;

import mygroup.controllers.executorsReq.CustomersFacade;
import mygroup.controllers.executorsReq.RequestCommand;
import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;

import java.util.List;

public abstract class CRUDCommand implements RequestCommand {
    protected final CustomerReWriter customerReWriter;
    private final CustomersFacade customersFacade = new CustomersFacade();

    protected CRUDCommand(CustomerReWriter customerReWriter) {
        this.customerReWriter = customerReWriter;
    }

    public List<Customer> getCustomerList(Request request) {
        return customersFacade.getCustomerList(request);
    }

}
