package view;

import lombok.Builder;
import lombok.Getter;
import view.dtoVw.ClientVw;

import java.util.List;

public @Builder class Request {
    private @Getter String sourceFolder;
    private @Getter String targetFolder;
    private @Getter List<ClientVw> clientList;
    private @Getter TypeRequest typeRequest;

    public enum TypeRequest {
        DeleteClients,
        AddClient,
        DeleteBases,
        AddBases,
        GetClients,
        Start
    }


}
