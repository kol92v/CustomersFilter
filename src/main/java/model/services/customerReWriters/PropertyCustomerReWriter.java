package model.services.customerReWriters;

import lombok.SneakyThrows;
import model.dtoMd.Customer;
import model.dtoMd.UpdateFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

public class PropertyCustomerReWriter extends CustomerReWriter {


    @SneakyThrows(IOException.class)
    public void addCustomerInFile(Customer customer) {
        Properties customersProperty = new Properties();
        if (Files.exists(fileCustomerSettings)) {
            customersProperty.load(Files.newBufferedReader(fileCustomerSettings));
        } else {
            Files.createFile(fileCustomerSettings);
        }
        StringBuilder updateFiles = new StringBuilder();
        for (UpdateFile update : customer.getUpdateFileList()) {
            updateFiles.append(update.getName()).append(";");
        }
        customersProperty.setProperty(customer.getName(), updateFiles.toString().trim());
        customersProperty.store(new FileOutputStream(fileCustomerSettings.toFile()),
                LocalDateTime.now().toString());
    }

    @SneakyThrows(IOException.class)
    @Override
    public List<Customer> getCustomersFromFile() {
        if (Files.notExists(fileCustomerSettings)) return Collections.emptyList();
        List<Customer> customers = new ArrayList<>();
        Properties customersProperty = new Properties();
        customersProperty.load(Files.newBufferedReader(fileCustomerSettings));
        customersProperty.forEach((o, o2) -> {
            Customer customerFromProp = new Customer((String) o);
            String[] updates = ((String) o2).split(";");
            for (String update : updates)
                customerFromProp.getUpdateFileList().add(new UpdateFile(update));
            customers.add(customerFromProp);
        });
        return customers;
    }

    @SneakyThrows
    @Override
    public void deleteCustomerInFile(Customer customer) {
        Properties customersProperty = new Properties();
        if (Files.notExists(fileCustomerSettings)) return;
        customersProperty.load(Files.newBufferedReader(fileCustomerSettings));
        customersProperty.remove(customer.getName());
        customersProperty.store(new FileOutputStream(fileCustomerSettings.toFile()),
                LocalDateTime.now().toString());
    }

    @Override
    public void rewriteCustomerInFile(Customer customer) {
        addCustomerInFile(customer);
    }
}
