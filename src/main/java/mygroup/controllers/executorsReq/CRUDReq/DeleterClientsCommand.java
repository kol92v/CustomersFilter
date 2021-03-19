package mygroup.controllers.executorsReq.CRUDReq;

import mygroup.controllers.converters.CnvrtCustomerClient;
import mygroup.controllers.executorsReq.CRUDReq.CRUDCommand;
import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class DeleterClientsCommand extends CRUDCommand {

    public DeleterClientsCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeRequest() {
        return TypeMessage.DeleteClients;
    }

    @Override
    public Response executeRequest(Request request) {
        List<Customer> customerList = new ArrayList<>();
        getCustomerList(request).forEach(customer ->
            customerList.add(customerReWriter.deleteCustomerInFile(customer)));
        return Response.builder().typeMessage(request.getTypeMessage())
                .clientVwList(CnvrtCustomerClient.customersToClients(customerList))
                .build();
    }
}
