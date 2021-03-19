package mygroup.controllers;


import view.Request;
import view.Response;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    private final Map<Request.TypeController, ControllerCommand> mapController = new HashMap<>();
    {
        mapController.put(Request.TypeController.CRUDProperty, new ControllerCRUDProp());
        mapController.put(Request.TypeController.PckDirectory, new ControllerPckDir());
    }

    public Response processingRequest(Request request) {
        return mapController.get(request.getTypeController()).processingRequest(request);
    }
}
