package view.viewFX.elementsGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import mygroup.controllers.MainController;
import view.Request;
import view.TypeMessage;
import view.View;
import view.dtoVw.BaseVw;
import view.dtoVw.ClientVw;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;

public class AdderClient implements View {
    @Getter @Setter
    private VBox vBoxClients;
    @Getter @Setter
    private VBox vBoxBasesANC;
    @Getter @Setter
    private TextField clientNameANC;

    private Stage objForClose;
    private final MainController mainController = new MainController();

    private final URL baseInTableFXML = new URL("file:" + Paths.get(System.getProperty("user.dir") +
            File.separator +  "target" + File.separator + "classes" +
            File.separator + "baseANC.fxml"));

    private static AdderClient instance =null;
    private AdderClient() throws MalformedURLException {}
    @SneakyThrows
    public static AdderClient getInstance() {
        if (instance == null) {
            synchronized (AdderClient.class) {
                if (instance ==null) instance = new AdderClient();
            }
        }
        return instance;
    }

    @SneakyThrows
    @Override
    public void runView() {
        start();
    }

    public void start() throws Exception {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader
                .load(new URL("file:" + Paths.get(System.getProperty("user.dir") +
                        File.separator +  "target" + File.separator + "classes" +
                        File.separator + "addNewClient.fxml")));
        primaryStage.setTitle("Add new client");
        primaryStage.setScene(new Scene(root, 344, 311));
        objForClose = primaryStage;
        primaryStage.showAndWait();


    }

    @SneakyThrows
    private BorderPane getBaseForTable() {
        return FXMLLoader.load(baseInTableFXML);
    }

    public void addNewLineBase() {
        vBoxBasesANC.getChildren().add(getBaseForTable());
    }

    public void removeLines() {
        Iterator<Node> nodeIterator = vBoxBasesANC.getChildren().iterator();
        while (nodeIterator.hasNext()) {
            BorderPane bpBase = (BorderPane) nodeIterator.next();
            if (isSelected(bpBase)) nodeIterator.remove();
        }
    }

    private boolean isSelected(BorderPane bpBase) {
        SplitPane splitPane = (SplitPane) bpBase.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(0);
        CheckBox checkBox = (CheckBox) anchorPane.getChildren().get(0);
        return checkBox.isSelected();
    }

    public void saveClient() {
        ClientVw clientVw = new ClientVw(clientNameANC.textProperty().getValue());
        for (Node node : vBoxBasesANC.getChildren()) {
            BorderPane bpBase = (BorderPane) node;
            String baseName = getBaseName(bpBase);
            if (baseName.length() != 0)
                clientVw.getBaseVwList().add(new BaseVw(baseName));
        }
        mainController.processingRequest(Request.builder()
                .typeController(Collections.singletonList(Request.TypeController.CRUDProperty))
                .typeMessage(TypeMessage.AddClient)
                .clientList(Collections.singletonList(clientVw))
                .build());
        clearAdder();
        clearMainWindow();
        closeWindow();
    }

    private String getBaseName(BorderPane bpBase) {
        SplitPane splitPane = (SplitPane) bpBase.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(1);
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        return textField.textProperty().getValue();
    }

    private void closeWindow() {
        objForClose.close();
    }

    private void clearAdder() {
        vBoxBasesANC.getChildren().clear();
        clientNameANC.textProperty().setValue("");
    }

    private void clearMainWindow() {
        vBoxClients.getChildren().clear();
        TableClients.getInstance().init();
    }







}
