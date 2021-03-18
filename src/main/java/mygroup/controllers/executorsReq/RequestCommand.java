package mygroup.controllers.executorsReq;

import mygroup.controllers.Converter;
import mygroup.controllers.executorsResp.ExecutorResponse;
import lombok.Getter;
import mygroup.dao.UpdateFinder;
import mygroup.services.dtoMd.Customer;
import mygroup.services.customerReWriters.CustomerReWriter;
import view.Request;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class RequestCommand {
    private final CustomerReWriter customerReWriter;
    private final UpdateFinder updateFinder;
    private final ExecutorResponse executorResponse;

    public RequestCommand(CustomerReWriter customerReWriter, UpdateFinder updateFinder, ExecutorResponse executorResponse) {
        this.customerReWriter = customerReWriter;
        this.updateFinder = updateFinder;
        this.executorResponse = executorResponse;
    }

    List<Customer> getCustomerList(Request request) {
        List<Customer> customerList = new ArrayList<>();
        request.getClientList().forEach(clientVw ->
                customerList.add(Converter.clientWvToCustomerMd(clientVw))
        );
        return customerList;
    }

    abstract Request.TypeRequest getType();
    abstract void executeRequest(Request request);
}
