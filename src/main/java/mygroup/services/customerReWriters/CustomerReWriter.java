package mygroup.services.customerReWriters;

import mygroup.services.dtoMd.Customer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class CustomerReWriter {
    final Path fileCustomerSettings = Paths.get(System.getProperty("user.dir")
            + File.separator + "customersSettings" + File.separator + "customers.properties");


    public abstract void addCustomerInFile(Customer customer);

    public abstract List<Customer> getCustomersFromFile();

    public abstract void deleteCustomerInFile(Customer customer);

    public abstract void rewriteBasesCustomerInFile(Customer customer);
}
