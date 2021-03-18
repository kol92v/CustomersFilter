package mygroup.controllers.executorsReq;


import mygroup.controllers.executorsResp.ExecutorResponse;
import mygroup.dao.UpdateFinder;
import mygroup.services.dtoMd.Customer;
import mygroup.services.creatorsPackage.CreatorPackage;
import mygroup.services.creatorsPackage.CreatorPackageFolder;
import mygroup.services.customerReWriters.CustomerReWriter;
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
