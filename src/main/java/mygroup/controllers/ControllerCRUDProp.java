package mygroup.controllers;


import mygroup.controllers.ControllerCommand;
import mygroup.controllers.executorsReq.CRUDReq.*;
import mygroup.services.customerReWriters.CustomerReWriter;
import mygroup.services.customerReWriters.PropertyCustomerReWriter;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.HashMap;
import java.util.Map;

public class ControllerCRUDProp implements ControllerCommand {
    private final Map<TypeMessage, CRUDCommand> mapCRUD = new HashMap<>();
    {
        CustomerReWriter customerReWriter = new PropertyCustomerReWriter();
        mapCRUD.put(TypeMessage.DeleteClients, new DeleterClientsCommand(customerReWriter));
        mapCRUD.put(TypeMessage.AddClient, new AdderClientCommand(customerReWriter));
        mapCRUD.put(TypeMessage.DeleteBases, new DeleterBasesCommand(customerReWriter));
        mapCRUD.put(TypeMessage.AddBases, new AdderBasesCommand(customerReWriter));
        mapCRUD.put(TypeMessage.GetClients, new GetterClientsCommand(customerReWriter));
        mapCRUD.put(TypeMessage.GetBases, new GetterBasesCommand(customerReWriter));
    }

    @Override
    public Response processingRequest(Request request) {
        return mapCRUD.get(request.getTypeMessage()).executeRequest(request);
    }

    @Override
    public Request.TypeController getTypeController() {
        return Request.TypeController.CRUDProperty;
    }
}
