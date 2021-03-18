package mygroup.controllers.executorsResp;

import view.Response;

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
