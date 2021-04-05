package mygroup.services.creatorsPackage;

import lombok.SneakyThrows;
import mygroup.services.dtoMd.Customer;
import mygroup.services.dtoMd.UpdateFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class CreatorPackageFolder extends CreatorPackage{

    public CreatorPackageFolder(Customer customer, Map<LocalDate, Map<String, List<Path>>> mapOfPathsUpdateFiles, Path folderForPackage) {
        super(customer, mapOfPathsUpdateFiles, folderForPackage);
    }

    @Override
    public void run() {
        Path folderForCopy = createFolderForCopy();
        for (Map.Entry<LocalDate, Map<String, List<Path>>> mapFoldersUpdates : mapOfPathsUpdateFiles.entrySet()) {
            LocalDate ldFolderOfOneDay = mapFoldersUpdates.getKey();
            if (ldFolderOfOneDay.isAfter(customer.getFrom().minusDays(1L)) && ldFolderOfOneDay.isBefore(customer.getTo().plusDays(1L))) {
                for (Map.Entry<String, List<Path>> listUpdateFiles : mapFoldersUpdates.getValue().entrySet()) {
                    if (customerHaveBase(listUpdateFiles.getKey())) {
                        listUpdateFiles.getValue().forEach(updateFile -> copyFile(updateFile, folderForCopy));
                    }
                }
            }
        }
    }

    @SneakyThrows(IOException.class)
    private Path createFolderForCopy() {
        if (Files.notExists(folderForPackage)) Files.createDirectory(folderForPackage);
        Path directoryForCustomer = Paths.get(folderForPackage.toString()
                + File.separator + customer.getName() + "_" + LocalDate.now().toString());
        if (Files.exists(directoryForCustomer)) {
            directoryForCustomer = Paths.get(directoryForCustomer.toString() + "_" + LocalDateTime.now().getNano());
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
