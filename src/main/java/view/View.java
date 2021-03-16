package view;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public interface View extends Runnable {
    BlockingQueue<Request> requestBlockingQueue = new LinkedBlockingQueue<>();
    BlockingQueue<Response> responseBlockingQueue = new LinkedBlockingQueue<>();

    default BlockingQueue<Request> getRequestBlockingQueue() {
        return requestBlockingQueue;
    }

    default BlockingQueue<Response> getResponseBlockingQueue() {
        return responseBlockingQueue;
    }
}
