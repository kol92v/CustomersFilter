package mygroup.controllers.executorsReq;

import view.Request;
import view.Response;
import view.TypeMessage;

public interface RequestCommand {

    TypeMessage getTypeMessage();
    Response executeRequest(Request request);
}
