package mygroup.services.customerReWriters;

import lombok.SneakyThrows;
import mygroup.services.dtoMd.Customer;
import mygroup.services.dtoMd.UpdateFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

public class PropertyCustomerReWriter extends CustomerReWriter {

    @SneakyThrows(IOException.class)
    private Properties getProperties() {
        Properties customersProperty = new Properties();
        if (Files.exists(fileCustomerSettings)) {
            customersProperty.load(Files.newBufferedReader(fileCustomerSettings));
        } else {
            Files.createFile(fileCustomerSettings);
        }
        return customersProperty;
    }

    @SneakyThrows(IOException.class)
    private void saveProperties(Properties customersProperty) {
        customersProperty.store(new FileOutputStream(fileCustomerSettings.toFile()),
                LocalDateTime.now().toString());
    }

    @SneakyThrows(CloneNotSupportedException.class)
    public Customer addCustomerInFile(Customer customer) {
        Properties customersProperty = getProperties();
        StringBuilder updateFiles = new StringBuilder();
        for (UpdateFile update : customer.getUpdateFileList()) {
            updateFiles.append(update.getName()).append(";");
        }
        customersProperty.setProperty(customer.getName(), updateFiles.toString().trim());
        saveProperties(customersProperty);
        return customer.clone();
    }


    @Override
    public List<Customer> getCustomersFromFile() {
        if (Files.notExists(fileCustomerSettings)) return Collections.emptyList();
        List<Customer> customers = new ArrayList<>();
        Properties customersProperty = getProperties();
        customersProperty.forEach((o, o2) -> {
            Customer customerFromProp = new Customer((String) o);
            String[] updates = ((String) o2).split(";");
            for (String update : updates)
                customerFromProp.getUpdateFileList().add(new UpdateFile(update));
            customers.add(customerFromProp);
        });
        return customers;
    }

    @Override
    public Customer getBasesCustomerFromFile(Customer customer) {
        for (Customer c : getCustomersFromFile())
            if (c.getName().equals(customer.getName())) return c;
        return new Customer("");
    }

    @SneakyThrows(CloneNotSupportedException.class)
    @Override
    public Customer deleteCustomerInFile(Customer customer) {
        if (Files.notExists(fileCustomerSettings)) return customer.clone();
        Properties customersProperty = getProperties();
        customersProperty.remove(customer.getName());
        saveProperties(customersProperty);
        return customer.clone();
    }

    @Override
    public Customer addBasesCustomerInFile(Customer customer) {
        return addCustomerInFile(customer);
    }

    @Override
    public Customer deleteBasesCustomerInFile(Customer customer) {
        return addCustomerInFile(customer);
    }
}
