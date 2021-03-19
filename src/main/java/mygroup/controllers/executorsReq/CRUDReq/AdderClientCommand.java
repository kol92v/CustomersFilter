package mygroup.controllers.executorsReq.CRUDReq;

import mygroup.controllers.executorsReq.CRUDReq.CRUDCommand;
import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

public class AdderClientCommand extends CRUDCommand {

    public AdderClientCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeRequest() {
        return TypeMessage.AddClient;
    }

    @Override
    public Response executeRequest(Request request) {
        getCustomerList(request).forEach(customerReWriter::addCustomerInFile);
    }
}
