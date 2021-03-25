package mygroup.controllers.executorsReq;

import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.List;

public interface ResponseCommand<T> {

    TypeMessage getTypeMessage();
    Response createResponse(TypeMessage typeMessage, List<T> result);

}
