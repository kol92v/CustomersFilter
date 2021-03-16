package view.viewFX.controllersFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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

    private static TextFormatter.Change regexDate(TextFormatter.Change change) {
        if (change.getControlNewText().matches("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"))
            return change;
        return null;
    }

    @FXML
    void initialize() {
        dateFromGeneral.setTextFormatter(new TextFormatter<String>(ControllerGeneralWindow::regexDate));

    }

    
}
