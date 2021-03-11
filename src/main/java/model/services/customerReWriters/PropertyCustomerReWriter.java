package model.services.customerReWriters;

import lombok.SneakyThrows;
import model.Customer;
import model.UpdateFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class PropertyCustomerReWriter extends CustomerReWriter {
    public PropertyCustomerReWriter(List<String> parametersList) {
        super(parametersList);
    }

    @SneakyThrows(IOException.class)
    public void saveCustomerInFile() {
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
    public List<Customer> loadCustomersFromFile() {
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
}
