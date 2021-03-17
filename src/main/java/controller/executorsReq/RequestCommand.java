package controller.executorsReq;

import controller.Converter;
import controller.executorsResp.ExecutorResponse;
import lombok.Getter;
import model.dao.UpdateFinder;
import model.dtoMd.Customer;
import model.services.customerReWriters.CustomerReWriter;
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
