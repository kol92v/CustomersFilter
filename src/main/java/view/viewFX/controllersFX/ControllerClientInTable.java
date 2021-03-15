package view.viewFX.controllersFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ControllerClientInTable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField dateFrom;

    @FXML
    private TextField dateTo;

    @FXML
    void initialize() {
        assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'clientInTable.fxml'.";
        assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'clientInTable.fxml'.";
        assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'clientInTable.fxml'.";

    }
}
