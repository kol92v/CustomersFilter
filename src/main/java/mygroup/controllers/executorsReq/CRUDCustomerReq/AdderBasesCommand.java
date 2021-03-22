package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class AdderBasesCommand extends CRUDCustomerCommand {
    public AdderBasesCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.AddBases;
    }

    @Override
    public Response executeRequest(Request request) {
        List<Customer> addedBases = new ArrayList<>();
        getCustomerList(request).forEach(customer ->
            addedBases.add(customerReWriter.addBasesCustomerInFile(customer)));
        return createResponse(request.getTypeMessage(), addedBases);
    }
}
