package model.services.workersForSettings;

import model.Customer;
import model.UpdateFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class SaverSettings {
    private List<String> parametersList;
    private final Path directoryForFilesSettings = Paths.get(System.getProperty("user.dir"));
    private Customer customer;

    public SaverSettings(List<String> parametersList) {
        this.parametersList = parametersList;
        this.customer = initCustomer();
    }

    private Customer initCustomer() {
        Customer customer = new Customer();
        customer.setName(parametersList.get(0));
        if (parametersList.size() == 1) return customer;
        for (int i = 1; i < parametersList.size(); i++) {
            customer.getUpdateFileList().add(new UpdateFile(parametersList.get(i)));
        }
        return customer;
    }

    public abstract void saveCustomer();
}
