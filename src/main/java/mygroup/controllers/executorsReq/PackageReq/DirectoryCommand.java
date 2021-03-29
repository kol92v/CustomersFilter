package mygroup.controllers.executorsReq.PackageReq;

import lombok.SneakyThrows;
import mygroup.dao.UpdateFinder;
import mygroup.services.creatorsPackage.CreatorPackage;
import mygroup.services.creatorsPackage.CreatorPackageFolder;
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

public class DirectoryCommand extends PackageCommand{


    public DirectoryCommand(UpdateFinder updateFinder) {
        super(updateFinder);
    }

    @Override
    public TypeMessage getTypeMessage() {
        return TypeMessage.StartToPackage;
    }

    @SneakyThrows(InterruptedException.class)
    @Override
    public Response executeRequest(Request request) {
        ExecutorService executor = Executors.newWorkStealingPool();
        Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles = getMapOfPathsUpdateFiles(request);
        getPackageCreators(request, mapOfPathsUpdateFiles).forEach(executor::execute);
        executor.invokeAll(new ArrayList<>());
        //stub
        return Response.builder().typeMessage(TypeMessage.StartToPackage).build();
    }

    private Map<LocalDate, Map<String, Path>> getMapOfPathsUpdateFiles(Request request) {
        return updateFinder.generateMapOfFilesPaths(request.getSourceFolder(),
                getDates(request)[0],
                getDates(request)[1]);
    }

    private LocalDate[] getDates(Request request) {
        LocalDate[] fromAndToDates = {LocalDate.now(), LocalDate.MIN.plusDays(1L)};
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
