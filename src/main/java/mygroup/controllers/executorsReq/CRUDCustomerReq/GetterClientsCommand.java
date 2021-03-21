package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

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
        customerReWriter.getCustomersFromFile();
    }
}
