package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class DeleterClientsCommand extends CRUDCustomerCommand {

    public DeleterClientsCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.DeleteClients;
    }

    @Override
    public Response executeRequest(Request request) {
        List<Customer> remoteCustomers = new ArrayList<>();
        getCustomerList(request).forEach(customer ->
            remoteCustomers.add(customerReWriter.deleteCustomerInFile(customer)));
        return createResponse(request, remoteCustomers);
    }

}
