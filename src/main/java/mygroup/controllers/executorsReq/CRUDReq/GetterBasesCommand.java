package mygroup.controllers.executorsReq.CRUDReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

public class GetterBasesCommand extends CRUDCommand{
    public GetterBasesCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeRequest() {
        return TypeMessage.GetBases;
    }

    @Override
    public Response executeRequest(Request request) {
        customerReWriter.getCustomersFromFile();
    }
}
