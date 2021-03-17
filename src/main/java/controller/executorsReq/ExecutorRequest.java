package controller.executorsReq;

import controller.Converter;
import controller.executorsResp.ExecutorResponse;
import model.dao.UpdateFinder;
import model.dtoMd.Customer;
import model.services.creatorsPackage.CreatorPackage;
import model.services.customerReWriters.CustomerReWriter;
import view.Request;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class ExecutorRequest {
    private final CustomerReWriter customerReWriter;
    private final UpdateFinder updateFinder;
    private final ExecutorResponse executorResponse;

    public ExecutorRequest(CustomerReWriter customerReWriter, UpdateFinder updateFinder, ExecutorResponse executorResponse) {
        this.customerReWriter = customerReWriter;
        this.updateFinder = updateFinder;
        this.executorResponse = executorResponse;
    }



    private final Map<Request, RequestCommand> requestCommandMap = new HashMap<>();

    public void executeRequest(Request request) {
        if (request.getTypeRequest().equals(Request.TypeRequest.DeleteClients))
            deleteClientsReq(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.AddClient))
            addClientReq(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.DeleteBases))
            deleteBasesReq(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.AddBases))
            addBasesReq(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.GetClients))
            getClientsReq();
        else if (request.getTypeRequest().equals(Request.TypeRequest.StartToPackage))
            startToPackageReq(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.GetBases))
            getBasesReq(request);
        else throw new UnsupportedOperationException();
    }

    List<Customer> getCustomerList(Request request) {
        List<Customer> customerList = new ArrayList<>();
        request.getClientList().forEach(clientVw ->
                customerList.add(Converter.clientWvToCustomerMd(clientVw))
        );
        return customerList;
    }

    void deleteClientsReq(Request request) {
        getCustomerList(request).forEach(customerReWriter::deleteCustomerInFile);
    }

    void addClientReq(Request request) {
        getCustomerList(request).forEach(customerReWriter::addCustomerInFile);
    }

    void deleteBasesReq(Request request) {
        getCustomerList(request).forEach(customerReWriter::rewriteBasesCustomerInFile);
    }

    void addBasesReq(Request request) {
        getCustomerList(request).forEach(customerReWriter::rewriteBasesCustomerInFile);
    }

    void getClientsReq() { //TODO
        customerReWriter.getCustomersFromFile();
    }

    void getBasesReq(Request request){ //TODO
        customerReWriter.getCustomersFromFile();

    }

    void startToPackageReq(Request request) {
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
                creatorPackages.add(getCreatorPackage(customer, mapOfPathsUpdateFiles, request))
        );
        return creatorPackages;
    }

    abstract CreatorPackage getCreatorPackage(Customer customer,
                                              Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles,
                                              Request request);

}
