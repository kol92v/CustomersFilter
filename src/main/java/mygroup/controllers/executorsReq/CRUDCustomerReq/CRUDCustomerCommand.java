package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.controllers.converters.CnvrtCustomerClient;
import mygroup.controllers.executorsReq.CustomersFacade;
import mygroup.controllers.executorsReq.RequestCommand;
import mygroup.controllers.executorsReq.ResponseCommand;
import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.List;

public abstract class CRUDCustomerCommand implements RequestCommand, ResponseCommand<Customer> {
    protected final CustomerReWriter customerReWriter;
    private final CustomersFacade customersFacade = new CustomersFacade();

    protected CRUDCustomerCommand(CustomerReWriter customerReWriter) {
        this.customerReWriter = customerReWriter;
    }

    public List<Customer> getCustomerList(Request request) {
        return customersFacade.getCustomerList(request);
    }

    @Override
    public Response createResponse(TypeMessage typeMessage, Customer... result) {
        return Response.builder().typeMessage(typeMessage)
                .clientVwList(CnvrtCustomerClient.customerToClient(result))
                .build();
    }
}
