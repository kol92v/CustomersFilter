package mygroup.services.creatorsPackage;

import mygroup.services.dtoMd.Customer;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public abstract class CreatorPackage implements Runnable {
    Customer customer;
    Map<LocalDate, Map<String, List<Path>>> mapOfPathsUpdateFiles;
    Path folderForPackage;

    public CreatorPackage(Customer customer, Map<LocalDate, Map<String, List<Path>>> mapOfPathsUpdateFiles, Path folderForPackage) {
        this.customer = customer;
        this.mapOfPathsUpdateFiles = mapOfPathsUpdateFiles;
        this.folderForPackage = folderForPackage;
    }
}
