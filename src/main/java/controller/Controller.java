package controller;


import controller.executorsReq.ExecutorRequest;
import controller.executorsReq.ExecutorRequestToFolder;
import controller.executorsResp.ExecutorResponse;
import lombok.SneakyThrows;
import model.dao.UpdateFinderFolderDate;
import model.dao.UpdateFinder;
import model.services.customerReWriters.CustomerReWriter;
import model.services.customerReWriters.PropertyCustomerReWriter;
import view.Request;
import view.View;
import view.viewFX.ViewFX;

public class Controller extends Thread {
    private final View view = new ViewFX();
    private final CustomerReWriter customerReWriter = new PropertyCustomerReWriter();
    private final UpdateFinder updateFinder = new UpdateFinderFolderDate();
    private final ExecutorResponse executorResponse = new ExecutorResponse(view.getResponseBlockingQueue());
    private final ExecutorRequest executorRequest = new ExecutorRequestToFolder(customerReWriter, updateFinder, executorResponse);

    private Controller() {
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        Thread viewThread = new Thread(controller.view);
        viewThread.start();
        controller.setDaemon(true);
        controller.start();
    }

    @SneakyThrows(InterruptedException.class)
    @Override
    public void run() {
        while (true) {
            Request requestFromView = view.getRequestBlockingQueue().take();
            executorRequest.executeRequest(requestFromView);
        }
    }
}
