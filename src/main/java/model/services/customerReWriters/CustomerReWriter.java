package model.services.customerReWriters;

import model.Customer;
import model.UpdateFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class CustomerReWriter {
    List<String> parametersList;
    final Path fileCustomerSettings = Paths.get(System.getProperty("user.dir")
            + File.separator + "customers.properties");
    Customer customer;

    public CustomerReWriter(List<String> parametersList) {
        this.parametersList = parametersList;
        this.customer = initCustomer();
    }

    private Customer initCustomer() {
        Customer customer = new Customer(parametersList.get(0));
        if (parametersList.size() == 1) return customer;
        for (int i = 1; i < parametersList.size(); i++) {
            customer.getUpdateFileList().add(new UpdateFile(parametersList.get(i)));
        }
        return customer;
    }

    public abstract void saveCustomerInFile();

    public abstract List<Customer> loadCustomersFromFile();
}
