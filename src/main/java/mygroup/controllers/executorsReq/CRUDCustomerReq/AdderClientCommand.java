package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

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
        getCustomerList(request).forEach(customerReWriter::addCustomerInFile);
    }
}
