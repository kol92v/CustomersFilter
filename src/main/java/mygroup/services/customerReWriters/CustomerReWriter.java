package mygroup.services.customerReWriters;

import mygroup.services.dtoMd.Customer;
import mygroup.services.dtoMd.UpdateFile;
import view.viewFX.ViewFX;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class CustomerReWriter {
    final Path fileCustomerSettings;
    {
        File currentClass = null;
        try {
            currentClass = new File(URLDecoder.decode(CustomerReWriter.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String classDirectory = currentClass.getParent() + File.separator + "customers.properties";
        fileCustomerSettings = new File(classDirectory).toPath();
    }


    public abstract Customer addCustomerInFile(Customer customer);

    public abstract List<Customer> getCustomersFromFile();

    public abstract Customer deleteCustomerInFile(Customer customer);

    public abstract Customer addBasesCustomerInFile(Customer customer);

    public abstract Customer deleteBasesCustomerInFile(Customer customer);

    public abstract Customer getBasesCustomerFromFile(Customer customer);
}
