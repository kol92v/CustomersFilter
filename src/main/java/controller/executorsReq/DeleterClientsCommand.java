package controller.executorsReq;

import controller.executorsResp.ExecutorResponse;
import model.dao.UpdateFinder;
import model.services.customerReWriters.CustomerReWriter;
import view.Request;

public class DeleterClientsCommand extends RequestCommand {
    public DeleterClientsCommand(CustomerReWriter customerReWriter, UpdateFinder updateFinder, ExecutorResponse executorResponse) {
        super(customerReWriter, updateFinder, executorResponse);
    }

    @Override
    public Request.TypeRequest getType() {
        return Request.TypeRequest.DeleteBases;
    }

    @Override
    public void executeRequest(Request request) {
        getCustomerList(request).forEach(getCustomerReWriter()::deleteCustomerInFile);
    }
}
