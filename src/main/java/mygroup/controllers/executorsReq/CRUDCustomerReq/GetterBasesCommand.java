package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

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
        customerReWriter.getCustomersFromFile();
    }
}
