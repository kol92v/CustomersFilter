package mygroup.controllers.converters;

import mygroup.services.dtoMd.Customer;
import mygroup.services.dtoMd.UpdateFile;
import view.dtoVw.BaseVw;
import view.dtoVw.ClientVw;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CnvrtCustomerClient {
    private CnvrtCustomerClient() {}

    public static ClientVw customerToClient(Customer customer) {
        ClientVw clientVw = new ClientVw(customer.getName());
        for (UpdateFile updateFile : customer.getUpdateFileList())
            clientVw.getBaseVwList().add(new BaseVw(updateFile.getName()));
        return clientVw;
    }

    public static Customer clientToCustomer(ClientVw clientVw) {
        Customer customer = Customer.builder()
                .to(clientVw.getDateTo())
                .from(clientVw.getDateFrom())
                .name(clientVw.getName()).build();
        clientVw.getBaseVwList().forEach(o -> customer.getUpdateFileList()
        .add(new UpdateFile(o.getName())));
        return customer;
    }

    public static List<ClientVw> customerToClient(List<Customer> customers) {
        return customers.stream().map(CnvrtCustomerClient::customerToClient)
                .collect(Collectors.toList());
    }

    public static List<Customer> clientToCustomer(List<ClientVw> clients) {
        return clients.stream().map(CnvrtCustomerClient::clientToCustomer)
                .collect(Collectors.toList());
    }

    public static List<ClientVw> customerToClient(Customer... customers) {
        return customerToClient(Arrays.asList(customers));
    }

    public static List<Customer> clientToCustomer(ClientVw... clients) {
        return clientToCustomer(Arrays.asList(clients));
    }


}
