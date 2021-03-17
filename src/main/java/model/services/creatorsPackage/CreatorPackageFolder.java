package model.services.creatorsPackage;

import lombok.SneakyThrows;
import model.dtoMd.Customer;
import model.dtoMd.UpdateFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class CreatorPackageFolder extends CreatorPackage{

    public CreatorPackageFolder(Customer customer, Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles, Path folderForPackage) {
        super(customer, mapOfPathsUpdateFiles, folderForPackage);
    }

    @Override
    public void run() {
        Path folderForCopy = createFolderForCopy();
        for (Map.Entry<LocalDate, Map<String, Path>> mapFoldersUpdates :mapOfPathsUpdateFiles.entrySet()) {
            LocalDate ldFolderOfOneDay = mapFoldersUpdates.getKey();
            if (ldFolderOfOneDay.isAfter(customer.getFrom().minusDays(1L)) && ldFolderOfOneDay.isBefore(customer.getTo().plusDays(1L))) {
                for (Map.Entry<String, Path> updateFile : mapFoldersUpdates.getValue().entrySet()) {
                    if (customerHaveBase(updateFile.getKey())) {
                        copyFile(updateFile.getValue(), folderForCopy);
                    }
                }
            }
        }
    }

    @SneakyThrows(IOException.class)
    private Path createFolderForCopy() {
        Path directoryForCustomer = Paths.get(folderForPackage.toString()
                + File.separator + customer.getName() + "_" + LocalDate.now().toString());
        if (Files.exists(directoryForCustomer)) {
            directoryForCustomer = Paths.get(directoryForCustomer.toString() + "_" + LocalDateTime.now().toString());
        }
        Files.createDirectory(directoryForCustomer);
        return directoryForCustomer;
    }

    private boolean customerHaveBase(String fileNameFromDB) {
        for (UpdateFile updateFile : customer.getUpdateFileList()) {
            if (updateFile.getName().equals(fileNameFromDB)) return true;
        }
        return false;
    }

    @SneakyThrows
    private void copyFile(Path updateFile, Path folderForCopy) {
        Path target = Paths.get(folderForCopy.toString() + File.separator + updateFile.getFileName());
        Files.copy(updateFile, target);
    }
}
