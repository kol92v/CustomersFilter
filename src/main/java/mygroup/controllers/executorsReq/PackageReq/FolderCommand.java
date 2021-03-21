package mygroup.controllers.executorsReq.PackageReq;

import mygroup.dao.UpdateFinder;
import mygroup.services.creatorsPackage.CreatorPackage;
import mygroup.services.creatorsPackage.CreatorPackageFolder;
import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FolderCommand extends PackageCommand{


    public FolderCommand(UpdateFinder updateFinder, CreatorPackage creatorPackage) {
        super(updateFinder, creatorPackage);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.StartToPackage;
    }

    @Override
    public Response executeRequest(Request request) {
        ExecutorService executor = Executors.newWorkStealingPool();
        getPackageCreators(request, getMapOfPathsUpdateFiles(request)).forEach(executor::execute);
    }

    private Map<LocalDate, Map<String, Path>> getMapOfPathsUpdateFiles(Request request) {
        return updateFinder.generateMapOfFilesPaths(request.getSourceFolder(),
                getDates(request)[0],
                getDates(request)[1]);
    }

    private LocalDate[] getDates(Request request) {
        LocalDate[] fromAndToDates = {LocalDate.now(), LocalDate.MIN};
        getCustomerList(request).forEach(customer -> {
            if (customer.getFrom().isBefore(fromAndToDates[0].plusDays(1L)))
                fromAndToDates[0] = customer.getFrom();
            if (customer.getTo().isAfter(fromAndToDates[1].minusDays(1L)))
                fromAndToDates[1] = customer.getTo();
        });
        return fromAndToDates;
    }

    private List<CreatorPackage> getPackageCreators(Request request, Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles) {
        List<CreatorPackage> creatorPackages = new ArrayList<>();
        getCustomerList(request).forEach(customer ->
                creatorPackages.add(new CreatorPackageFolder(customer, mapOfPathsUpdateFiles, request.getTargetFolder()))
        );
        return creatorPackages;
    }
}
