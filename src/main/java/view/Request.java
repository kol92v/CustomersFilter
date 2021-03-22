package view;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import view.dtoVw.ClientVw;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Request {
    private Path sourceFolder;
    private Path targetFolder;
    private List<ClientVw> clientList;
    private TypeMessage typeMessage;
    private List<TypeController> typeController;


    public enum TypeController {
        CRUD, CRUDProperty,
        PckDirectory, FinderFolderDate
    }

}
