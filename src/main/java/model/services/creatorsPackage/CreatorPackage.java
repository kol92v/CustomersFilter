package model.services.creatorsPackage;

import model.Customer;
import model.dao.FileUpdateFinder;
import java.nio.file.Path;

public abstract class CreatorPackage implements Runnable {
    Customer customer;
    FileUpdateFinder fileUpdateFinder;
    Path folderForPackage;

    public CreatorPackage(Customer customer, FileUpdateFinder fileUpdateFinder, Path folderForPackage) {
        this.customer = customer;
        this.fileUpdateFinder = fileUpdateFinder;
        this.folderForPackage = folderForPackage;
    }
}
