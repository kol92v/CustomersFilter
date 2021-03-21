package mygroup.controllers.executorsReq;

import mygroup.services.dtoMd.Customer;
import view.Request;
import view.Response;
import view.TypeMessage;

import java.util.List;

public interface ResponseCommand<T> {

    TypeMessage getTypeMessage();
    Response createResponse(Request request, T... result);
    default Response createResponse(Request request, List<T> result) {
        return createResponse(request, (T) result.toArray(new Object[0]));
    }
}
