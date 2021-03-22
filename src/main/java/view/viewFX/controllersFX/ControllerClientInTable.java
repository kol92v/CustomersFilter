package view.viewFX.controllersFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class ControllerClientInTable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Hyperlink clientName;

    @FXML
    private TextField dateFrom;

    @FXML
    private TextField dateTo;

    @FXML
    void initialize() {
        clientName.setOnAction(event -> System.out.println(clientName.textProperty().getValue()));
    }
}
