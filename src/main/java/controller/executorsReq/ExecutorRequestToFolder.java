package controller.executorsReq;


import controller.executorsResp.ExecutorResponse;
import model.dao.UpdateFinder;
import model.dtoMd.Customer;
import model.services.creatorsPackage.CreatorPackage;
import model.services.creatorsPackage.CreatorPackageFolder;
import model.services.customerReWriters.CustomerReWriter;
import view.Request;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;


public class ExecutorRequestToFolder extends ExecutorRequest {
    public ExecutorRequestToFolder(CustomerReWriter customerReWriter, UpdateFinder updateFinder, ExecutorResponse executorResponse) {
        super(customerReWriter, updateFinder, executorResponse);
    }

    CreatorPackage getCreatorPackage(Customer customer,
                                     Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles,
                                     Request request) {
        return new CreatorPackageFolder(customer, mapOfPathsUpdateFiles, request.getTargetFolder());
    }
}
