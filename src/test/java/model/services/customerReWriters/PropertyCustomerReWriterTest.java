package model.services.customerReWriters;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class PropertyCustomerReWriterTest {


    PropertyCustomerReWriter propertyCustomerReWriter = new PropertyCustomerReWriter(
            new ArrayList<>(Arrays.asList("karqiez", "acn", "bcn", "qwe")));

    @Test
    void saveCustomerInFile() {
        propertyCustomerReWriter.saveCustomerInFile();
    }

    @Test
    void loadCustomersFromFile() {
        propertyCustomerReWriter.loadCustomersFromFile().forEach(System.out::println);
    }
}