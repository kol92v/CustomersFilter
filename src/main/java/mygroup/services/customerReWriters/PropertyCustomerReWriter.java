package mygroup.services.customerReWriters;

import lombok.SneakyThrows;
import mygroup.services.dtoMd.Customer;
import mygroup.services.dtoMd.UpdateFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

public class PropertyCustomerReWriter extends CustomerReWriter {


    private Properties getProperties() {
        Properties customersProperty = new Properties();
        if (Files.exists(fileCustomerSettings)) {
            try(FileInputStream fis = new FileInputStream(fileCustomerSettings.toFile())) {
                customersProperty.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            try {
                Files.createFile(fileCustomerSettings);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customersProperty;
    }


    private void saveProperties(Properties customersProperty) {
        try(FileOutputStream fis = new FileOutputStream(fileCustomerSettings.toFile())) {
        customersProperty.store(fis,LocalDateTime.now().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows(CloneNotSupportedException.class)
    public Customer addCustomerInFile(Customer customer) {
        Properties customersProperty = getProperties();
        StringBuilder updateFiles = new StringBuilder();
        for (UpdateFile update : customer.getUpdateFileList()) {
            updateFiles.append(update.getName()).append(";");
        }
        if (updateFiles.indexOf("CONS;") == -1) updateFiles.append("CONS;");
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

    @SneakyThrows(CloneNotSupportedException.class)
    @Override
    public Customer addBasesCustomerInFile(Customer customer) {
        Properties properties = getProperties();
        StringBuilder bases = new StringBuilder(properties.getProperty(customer.getName()));
        for (UpdateFile updateFile : customer.getUpdateFileList()) {
            bases.append(updateFile.getName()).append(";");
        }
        properties.setProperty(customer.getName(), bases.toString());
        saveProperties(properties);
        return customer.clone();
    }

    @SneakyThrows(CloneNotSupportedException.class)
    @Override
    public Customer deleteBasesCustomerInFile(Customer customer) {
        Customer customerFromProp = getCustomerFromFile(customer);
        Iterator<UpdateFile> updateFileIterator = customerFromProp.getUpdateFileList().iterator();
        while (updateFileIterator.hasNext()) {
            UpdateFile updateFileProp = updateFileIterator.next();
            for (UpdateFile delFile : customer.getUpdateFileList()) {
                if (updateFileProp.getName().equals(delFile.getName()))
                    updateFileIterator.remove();
            }
        }
        addCustomerInFile(customerFromProp);
        return customer.clone();
    }

    private Customer getCustomerFromFile(Customer customer) {
        for (Customer c : getCustomersFromFile())
            if (c.getName().equals(customer.getName())) return c;
        return customer;
    }
}
