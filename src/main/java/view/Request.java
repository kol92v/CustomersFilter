package view;

import lombok.Builder;
import lombok.Getter;
import view.dtoVw.ClientVw;

import java.nio.file.Path;
import java.util.List;
@Getter
public @Builder class Request {
    private Path sourceFolder;
    private Path targetFolder;
    private List<ClientVw> clientList;
    private TypeRequest typeRequest;

    public enum TypeRequest {
        DeleteClients,
        AddClient,
        DeleteBases,
        AddBases,
        GetClients,
        GetBases,
        StartToPackage
    }

    public enum TypeController {
        CRUDProperty,
        PckDir
    }


}
