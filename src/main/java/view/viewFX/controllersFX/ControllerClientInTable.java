package view.viewFX.controllersFX;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import lombok.Getter;
import mygroup.controllers.MainController;
import view.Request;
import view.Response;
import view.TypeMessage;
import view.dtoVw.ClientVw;
import view.viewFX.elementsGUI.TableBases;
import view.viewFX.elementsGUI.TableClients;

@Getter
public class ControllerClientInTable {
    private final MainController mainController = new MainController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkBoxCIT;

    @FXML
    private Hyperlink clientNameCIT;

    @FXML
    private TextField dateFromCIT;

    @FXML
    private TextField dateToCIT;

    @FXML
    void initialize() {
        TableBases tableBases = TableBases.getInstance();
        TableClients tableClients = TableClients.getInstance();

        initListeners(tableBases, tableClients);

    }

    private void initListeners(TableBases tableBases, TableClients tableClients) {
        clientNameCIT.setOnAction(event -> initClientNameCIT(tableBases));
    }

    private void initClientNameCIT(TableBases tableBases) {
        tableBases.clearBases();
        Response response = mainController.processingRequest(Request.builder().
                typeController(Collections.singletonList(Request.TypeController.CRUDProperty)).
                typeMessage(TypeMessage.GetBases).
                clientList(Collections.singletonList(new ClientVw(clientNameCIT.textProperty().getValue()))).
                build());
        tableBases.addBases(response.getClientVwList());
    }
}
