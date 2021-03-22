package mygroup.controllers;

import mygroup.controllers.executorsReq.PackageReq.DirectoryCommand;
import mygroup.controllers.executorsReq.PackageReq.PackageCommand;
import mygroup.dao.UpdateFinder;
import mygroup.dao.UpdateFinderFolderDate;
import view.Request;
import view.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerPckDir implements ControllerCommand {
    Map<Request.TypeController, UpdateFinder> finderMap = new HashMap<>();
    {
        finderMap.put(Request.TypeController.FinderFolderDate, new UpdateFinderFolderDate());
    }


    @Override
    public Response processingRequest(Request request) {
        PackageCommand packageCommand = new DirectoryCommand(finderMap.get(request.getTypeController().get(1)));
        return packageCommand.executeRequest(request);
    }

    @Override
    public Request.TypeController getTypeController() {
        return Request.TypeController.PckDirectory;
    }
}
