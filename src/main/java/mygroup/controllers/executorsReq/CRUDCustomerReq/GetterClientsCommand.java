package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;
import java.util.List;

public class GetterClientsCommand extends CRUDCustomerCommand {
    public GetterClientsCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.GetClients;
    }

    @Override
    public Response executeRequest(Request request) {
        List<Customer> customerList = customerReWriter.getCustomersFromFile();
        return createResponse(request.getTypeMessage(), customerList);
    }
}
