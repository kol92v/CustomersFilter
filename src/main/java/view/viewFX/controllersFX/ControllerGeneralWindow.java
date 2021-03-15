package view.viewFX.controllersFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ControllerGeneralWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane mainBorderPain;

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
    private Label lbClientBasesList;

    @FXML
    private VBox vboxBases;

    @FXML
    private Button startButton;

    @FXML
    void initialize() {


    }

    
}
