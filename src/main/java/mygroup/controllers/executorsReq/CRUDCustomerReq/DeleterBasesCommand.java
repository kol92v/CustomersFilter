package mygroup.controllers.executorsReq.CRUDCustomerReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

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
        getCustomerList(request).forEach(customerReWriter::rewriteBasesCustomerInFile);
    }
}
