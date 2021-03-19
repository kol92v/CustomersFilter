package mygroup.controllers.creatorsResp;

import mygroup.controllers.converters.CnvrtCustomerClient;
import mygroup.services.dtoMd.Customer;
import view.Response;
import view.TypeMessage;

import java.util.List;

public class CRUDResp {


    public Response getResponse(List<Customer> customerList, TypeMessage typeRequest) {
        Response response = Response.builder().typeMessage()
        customerList.stream().map(CnvrtCustomerClient::customerToClient)
                .forEach(clientVw -> response.getClientVwList().add(clientVw));
    }

    public Response getResponse(Customer customer, TypeMessage typeRequest) {

    }
}
