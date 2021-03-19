package mygroup.controllers.executorsReq.CRUDReq;

import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

public class AdderBasesCommand extends CRUDCommand{
    public AdderBasesCommand(CustomerReWriter customerReWriter) {
        super(customerReWriter);
    }

    @Override
    public TypeMessage getTypeRequest() {
        return TypeMessage.AddBases;
    }

    @Override
    public Response executeRequest(Request request) {
        getCustomerList(request).forEach(customerReWriter::rewriteBasesCustomerInFile);
    }
}
