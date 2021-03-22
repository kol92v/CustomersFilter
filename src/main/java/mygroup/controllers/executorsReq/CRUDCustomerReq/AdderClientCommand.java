package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class AdderClientCommand extends CRUDCustomerCommand {

    public AdderClientCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.AddClient;
    }

    @Override
    public Response executeRequest(Request request) {
        List<Customer> addedCustomers = new ArrayList<>();
        getCustomerList(request).forEach(customer ->
                addedCustomers.add(customerReWriter.addCustomerInFile(customer)));
        return createResponse(request.getTypeMessage(), addedCustomers);
    }
}
