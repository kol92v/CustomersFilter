package view.viewFX.controllersFX;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;

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

    @SneakyThrows
    @FXML
    void initialize() {
        dateFromGeneral.setTextFormatter(new TextFormatter<String>(ControllerGeneralWindow::regexDate));


        BorderPane borderPane = getClientInTable();
        SplitPane splitPane = (SplitPane) borderPane.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(1);
        Hyperlink clientName = (Hyperlink) anchorPane.getChildren().get(0);
        clientName.textProperty().setValue("hi");
        System.out.println(clientName);
        vboxClients.getChildren().add(borderPane);

        BorderPane borderPane1 = getClientInTable();
        SplitPane splitPane1 = (SplitPane) borderPane1.getChildren().get(0);
        AnchorPane anchorPane1 = (AnchorPane) splitPane1.getItems().get(1);
        Hyperlink clientName1 = (Hyperlink) anchorPane1.getChildren().get(0);
        clientName1.textProperty().setValue("hi2");
        vboxClients.getChildren().add(borderPane1);

//        clientName.setOnAction(event -> System.out.println(clientName.textProperty().getValue()));
//        clientName1.setOnAction(event -> System.out.println(clientName1.textProperty().getValue()));

    }

    @SneakyThrows
    private BorderPane getClientInTable() {
        return FXMLLoader
                .load(new URL("file:" + Paths.get(System.getProperty("user.dir").toString() +
                        File.separator +  "target" + File.separator + "classes" +
                        File.separator + "clientInTable.fxml")));
    }

    
}
