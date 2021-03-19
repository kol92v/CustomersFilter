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
    protected CreatorPackage creatorPackage;

    public PackageCommand(UpdateFinder updateFinder, CreatorPackage creatorPackage) {
        this.updateFinder = updateFinder;
        this.creatorPackage = creatorPackage;
    }

    public List<Customer> getCustomerList(Request request) {
        return customersFacade.getCustomerList(request);
    }
}
