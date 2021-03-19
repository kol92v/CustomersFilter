package mygroup.controllers.executorsReq.CRUDReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

public class DeleterBasesCommand extends CRUDCommand{
    public DeleterBasesCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeRequest() {
        return TypeMessage.DeleteBases;
    }

    @Override
    public Response executeRequest(Request request) {
        getCustomerList(request).forEach(customerReWriter::rewriteBasesCustomerInFile);
    }
}
