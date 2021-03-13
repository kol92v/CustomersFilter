package model.services.customerReWriters;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomerReWriterTest {

    @Test
    void outputFileCustomerSettings() {
        CustomerReWriter customerReWriter = new PropertyCustomerReWriter(new ArrayList<>(Arrays.asList("re")));
        System.out.println(customerReWriter.fileCustomerSettings);
    }

}