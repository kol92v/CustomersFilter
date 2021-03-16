package model.services.creatorsPackage;

import model.dtoMd.Customer;
import model.dao.FileUpdateFinder;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;

public abstract class CreatorPackage implements Runnable {
    Customer customer;
    Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles;
    Path folderForPackage;

    public CreatorPackage(Customer customer, Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles, Path folderForPackage) {
        this.customer = customer;
        this.mapOfPathsUpdateFiles = mapOfPathsUpdateFiles;
        this.folderForPackage = folderForPackage;
    }
}
