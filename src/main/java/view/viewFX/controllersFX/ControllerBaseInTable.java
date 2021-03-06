package view.viewFX.controllersFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import lombok.Getter;
import mygroup.controllers.MainController;

@Getter
public class ControllerBaseInTable {
    private final MainController mainController = new MainController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label baseNameBIT;

    @FXML
    private CheckBox checkBoxBIT;

    @FXML
    void initialize() {

    }
}
