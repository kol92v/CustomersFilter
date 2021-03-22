package mygroup.controllers.executorsReq.PackageReq;

import mygroup.controllers.executorsReq.CustomersFacade;
import mygroup.controllers.executorsReq.RequestCommand;
import mygroup.dao.UpdateFinder;
import mygroup.services.creatorsPackage.CreatorPackage;
import mygroup.services.dtoMd.Customer;
import view.Request;

import java.util.List;

public abstract class PackageCommand implements RequestCommand {
    private final CustomersFacade customersFacade = new CustomersFacade();
    protected UpdateFinder updateFinder;

    public PackageCommand(UpdateFinder updateFinder) {
        this.updateFinder = updateFinder;
    }

    public List<Customer> getCustomerList(Request request) {
        return customersFacade.getCustomerList(request);
    }
}
