package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class DeleterBasesCommand extends CRUDCustomerCommand {
    public DeleterBasesCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.DeleteBases;
    }

    @Override
    public Response executeRequest(Request request) {
        List<Customer> remoteBases = new ArrayList<>();
        getCustomerList(request).forEach(customer ->
                remoteBases.add(customerReWriter.deleteBasesCustomerInFile(customer)));
        return createResponse(request.getTypeMessage(), remoteBases);
    }
}
