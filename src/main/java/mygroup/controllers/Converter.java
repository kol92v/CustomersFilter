package mygroup.controllers;

import mygroup.services.dtoMd.Customer;
import mygroup.services.dtoMd.UpdateFile;
import view.dtoVw.BaseVw;
import view.dtoVw.ClientVw;

public class Converter {
    private Converter() {}

    public static ClientVw customerMdToClientWv(Customer customer) {
        ClientVw clientVw = new ClientVw(customer.getName());
        for (UpdateFile updateFile : customer.getUpdateFileList())
            clientVw.getBaseVwList().add(new BaseVw(updateFile.getName()));
        return clientVw;
    }

    public static Customer clientWvToCustomerMd(ClientVw clientVw) {
        Customer customer = Customer.builder()
                .to(clientVw.getDateTo())
                .from(clientVw.getDateFrom())
                .name(clientVw.getName()).build();
        clientVw.getBaseVwList().forEach(o -> customer.getUpdateFileList()
        .add(new UpdateFile(o.getName())));
        return customer;
    }
}
