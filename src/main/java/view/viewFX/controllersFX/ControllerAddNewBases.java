package view.viewFX.controllersFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.viewFX.elementsGUI.AdderBases;

public class ControllerAddNewBases {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label clientNameANB;

    @FXML
    private VBox vBoxANB;

    @FXML
    private Button btRemoveBasesANB;

    @FXML
    private Button btSaveBasesANB;

    @FXML
    private Button btAddBaseANB;


    @FXML
    void initialize() {
        AdderBases adderBases = AdderBases.getInstance();
        adderBases.setClientNameANB(clientNameANB);
        adderBases.setVBoxANB(vBoxANB);
        initListeners(adderBases);
    }

    private void initListeners(AdderBases adderBases) {
        btAddBaseANB.setOnAction(event -> actionBtAddBaseANB(adderBases));
        btRemoveBasesANB.setOnAction(event -> actionBtRemoveBasesANB(adderBases));
        btSaveBasesANB.setOnAction(event -> actionBtSaveBasesANB(adderBases));
    }

    private void actionBtAddBaseANB(AdderBases adderBases) {
        adderBases.addBaseANB();
    }

    private void actionBtRemoveBasesANB(AdderBases adderBases) {
        adderBases.removeBaseANB();
    }

    private void actionBtSaveBasesANB(AdderBases adderBases) {
        adderBases.saveBasesANB();
    }
}
