package mygroup.controllers.executorsReq.CRUDReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

public class GetterClientsCommand extends CRUDCommand{
    public GetterClientsCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeRequest() {
        return TypeMessage.GetClients;
    }

    @Override
    public Response executeRequest(Request request) {
        customerReWriter.getCustomersFromFile();
    }
}
