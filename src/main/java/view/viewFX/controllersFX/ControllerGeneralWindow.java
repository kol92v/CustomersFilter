package view.viewFX.controllersFX;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.SneakyThrows;
import mygroup.controllers.MainController;
import view.Request;
import view.Response;
import view.TypeMessage;
import view.View;
import view.dtoVw.ClientVw;
import view.viewFX.elementsGUI.AdderBases;
import view.viewFX.elementsGUI.AdderClient;
import view.viewFX.elementsGUI.TableBases;
import view.viewFX.elementsGUI.TableClients;

@Getter
public class ControllerGeneralWindow {
    private final MainController mainController = new MainController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField pathSourceFolder;

    @FXML
    private TextField pathTargetFolder;

    @FXML
    private TextField dateFromGeneral;

    @FXML
    private TextField dateToGeneral;

    @FXML
    private Button btRemoveClient;

    @FXML
    private Button btAddClient;

    @FXML
    private VBox vboxClients;

    @FXML
    private Button btAddBase;

    @FXML
    private Button btRemoveBase;

    @FXML
    private Label clientNameBaseList;

    @FXML
    private VBox vboxBases;

    @FXML
    private Button startButton;

    @SneakyThrows
    @FXML
    void initialize() {
        setDatesFormat();

        TableBases tableBases = TableBases.getInstance();
        tableBases.setVBoxBases(vboxBases);
        tableBases.setClientNameBaseList(clientNameBaseList);

        TableClients tableClients = TableClients.getInstance();
        tableClients.setVBoxClients(vboxClients);

        tableClients.init();

        initListeners(tableClients, tableBases);

    }

    private void setDatesFormat() {
        TextFormatter<String> textFormatter = new TextFormatter<>(this::checkFieldDate);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(this::checkFieldDate);
        dateFromGeneral.setTextFormatter(textFormatter);
        dateToGeneral.setTextFormatter(textFormatter2);
    }

    private TextFormatter.Change checkFieldDate(TextFormatter.Change change) {
        if (change.getControlNewText().length() == 10) {
            if (change.getControlNewText().matches("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"))
                return change;
            return null;
        }
        return change;
    }



    private void initListeners(TableClients tableClients, TableBases tableBases) {
        btRemoveClient.setOnAction(event -> actionBtRemoveClient(tableClients));
        btAddClient.setOnAction(event -> actionBtAddClient());
        btRemoveBase.setOnAction(event -> actionBtRemoveBase(tableBases));
        btAddBase.setOnAction(event -> actionBtAddBase());
    }

    private void actionBtRemoveClient(TableClients tableClients) {
        List<ClientVw> clientVwList = tableClients.removeClients();
        mainController.processingRequest(Request.builder()
                .clientList(clientVwList)
                .typeMessage(TypeMessage.DeleteClients)
                .typeController(Collections.singletonList(Request.TypeController.CRUDProperty))
                .build());
        vboxBases.getChildren().clear();
    }

    private void actionBtAddClient() {
        AdderClient adderClient = AdderClient.getInstance();
        adderClient.setVBoxClients(vboxClients);
        adderClient.runView();
    }

    private void actionBtRemoveBase(TableBases tableBases) {
        List<ClientVw> clientVwList = tableBases.removeBases();
        mainController.processingRequest(Request.builder().
                typeController(Collections.singletonList(Request.TypeController.CRUDProperty)).
                typeMessage(TypeMessage.DeleteBases).
                clientList(clientVwList).
                build());
    }

    private void actionBtAddBase() {
        if (!vboxBases.getChildren().isEmpty()) {
            AdderBases adderBases = AdderBases.getInstance();
            adderBases.setVBoxBases(vboxBases);
            adderBases.setClientNameBaseList(clientNameBaseList);
            adderBases.runView();
        }
    }
}
