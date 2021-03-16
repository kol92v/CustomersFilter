package controller;


import lombok.SneakyThrows;
import view.Request;
import view.View;
import view.viewFX.ViewFX;

public class Controller extends Thread{
    private final View view = new ViewFX();

    private Controller(){}

    public static void main(String[] args) throws InterruptedException {
        Controller controller = new Controller();
        Thread viewThread = new Thread(controller.view);
        viewThread.start();
        controller.setDaemon(true);
        controller.start();
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Request requestFromView = view.getRequestBlockingQueue().take();
            executeRequest(requestFromView);
        }
    }

    private void executeRequest(Request request) {
        if (request.getTypeRequest().equals(Request.TypeRequest.DeleteClients))
            deleteClients(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.AddClient))
            addClient(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.DeleteBases))
            deleteBases(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.AddBases))
            addBases(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.GetClients))
            getClients(request);
        else if (request.getTypeRequest().equals(Request.TypeRequest.Start))
            startToPackage(request);
        else throw new UnsupportedOperationException();
    }

    private void startToPackage(Request request) {

    }

    private void getClients(Request request) {

    }

    private void addBases(Request request) {

    }

    private void deleteClients(Request request) {

    }

    private void addClient(Request request) {

    }

    private void deleteBases(Request request) {

    }


}
