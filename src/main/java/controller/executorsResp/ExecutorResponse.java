package controller.executorsResp;

import view.Request;
import view.Response;
import view.View;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ExecutorResponse {
    private BlockingQueue<Response> responseBlockingQueue;

    public ExecutorResponse(BlockingQueue<Response> responseBlockingQueue) {
        this.responseBlockingQueue = responseBlockingQueue;
    }

    public void updateBaseListRsp() {

    }

    public void updateClientListRsp() {

    }
}
