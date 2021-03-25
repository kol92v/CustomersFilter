package view.viewFX.controllersFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.SneakyThrows;
import view.viewFX.elementsGUI.AdderClient;

@Getter
public class ControllerAddNewClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vBoxBasesANC;

    @FXML
    private TextField clientNameANC;

    @FXML
    private Button btSaveClientANC;

    @FXML
    private Button btAddBaseANC;

    @FXML
    private Button btRemoveANC;

    @FXML
    void initialize() {
        AdderClient adderClient = AdderClient.getInstance();
        adderClient.setVBoxBasesANC(vBoxBasesANC);
        adderClient.setClientNameANC(clientNameANC);
        initListeners(adderClient);
    }

    private void initListeners(AdderClient adderClient) {
        btAddBaseANC.setOnAction(event -> actionBtAddBaseANC(adderClient));
        btRemoveANC.setOnAction(event -> actionBtRemoveANC(adderClient));
        btSaveClientANC.setOnAction(event -> actionBtSaveClientANC(adderClient));
    }

    @SneakyThrows
    private void actionBtAddBaseANC(AdderClient adderClient) {
        adderClient.addNewLineBase();
    }

    private void actionBtRemoveANC(AdderClient adderClient) {
        adderClient.removeLines();
    }

    private void actionBtSaveClientANC(AdderClient adderClient) {
        adderClient.saveClient();
//        close(new Event(btSaveClientANC, , null));
    }

//    private void close(Event event) {
//        ((Node)(event.getSource())).getScene().getWindow().hide();
//    }


}
