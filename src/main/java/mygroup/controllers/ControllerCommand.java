package mygroup.controllers;

import view.Request;
import view.Response;

public interface ControllerCommand {
    Response processingRequest(Request request);
    Request.TypeController getTypeController();
}
