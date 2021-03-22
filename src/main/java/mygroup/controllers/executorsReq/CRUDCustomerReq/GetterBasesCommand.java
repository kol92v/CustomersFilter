package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class GetterBasesCommand extends CRUDCustomerCommand {
    public GetterBasesCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.GetBases;
    }

    @Override
    public Response executeRequest(Request request) {
        List<Customer> receivedBases = new ArrayList<>();
        getCustomerList(request).forEach(customer ->
                receivedBases.add(customerReWriter.getBasesCustomerFromFile(customer)));
        return createResponse(request.getTypeMessage(), receivedBases);
    }
}
