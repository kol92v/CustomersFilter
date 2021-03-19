package mygroup.controllers.executorsReq;

import mygroup.controllers.converters.CnvrtCustomerClient;
import mygroup.services.dtoMd.Customer;
import view.Request;

import java.util.ArrayList;
import java.util.List;

public class CustomersFacade {
    public List<Customer> getCustomerList(Request request) {
        List<Customer> customerList = new ArrayList<>();
        request.getClientList().forEach(clientVw ->
                customerList.add(CnvrtCustomerClient.clientToCustomer(clientVw))
        );
        return customerList;
    }
}
