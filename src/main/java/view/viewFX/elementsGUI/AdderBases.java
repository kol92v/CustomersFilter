package view.viewFX.elementsGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
import view.Response;
import view.TypeMessage;
import view.View;
import view.dtoVw.BaseVw;
import view.dtoVw.ClientVw;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AdderBases implements View {
    private final MainController mainController = new MainController();

    @Getter
    @Setter
    private VBox vBoxBases;
    @Getter
    @Setter
    private Label clientNameBaseList;
    @Getter
    @Setter
    private Label clientNameANB;
    @Getter
    @Setter
    private VBox vBoxANB;

    private Stage objForClose;
    private static AdderBases instance = null;

    private AdderBases() {
    }

    public static AdderBases getInstance() {
        if (instance == null) {
            synchronized (AdderBases.class) {
                if (instance == null) instance = new AdderBases();
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
                .load(Thread.currentThread().getContextClassLoader().getResource("addNewBases.fxml"));
        primaryStage.setTitle("Add new bases");
        primaryStage.setScene(new Scene(root, 276, 333));
        objForClose = primaryStage;
        setClientNameInTable();
        primaryStage.showAndWait();
    }

    private void setClientNameInTable() {
        clientNameANB.textProperty().setValue(clientNameBaseList.textProperty().getValue());
    }

    private void closeWindow() {
        objForClose.close();
    }


    @SneakyThrows
    public void addBaseANB() {
        vBoxANB.getChildren().add(getBaseANB());
    }

    @SneakyThrows
    private Node getBaseANB() {
        return FXMLLoader
                .load(Thread.currentThread().getContextClassLoader().getResource("baseANB.fxml"));
    }


    public void removeBaseANB() {
        Iterator<Node> nodeIterator = vBoxANB.getChildren().iterator();
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

    public void saveBasesANB() {
        List<BaseVw> baseVwList = new ArrayList<>();
        for (Node node : vBoxANB.getChildren()) {
            baseVwList.add(createBase((BorderPane) node));
        }
        ClientVw clientVw = new ClientVw(clientNameBaseList.textProperty().getValue());
        clientVw.setBaseVwList(baseVwList);
        Response response = mainController.processingRequest(Request.builder()
                .typeController(Collections.singletonList(Request.TypeController.CRUDProperty))
                .typeMessage(TypeMessage.AddBases)
                .clientList(Collections.singletonList(clientVw))
                .build());
        TableBases.getInstance().addBases(response.getClientVwList());

        closeWindow();
    }

    private BaseVw createBase(BorderPane bpBase) {
        SplitPane splitPane = (SplitPane) bpBase.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(1);
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        return new BaseVw(textField.textProperty().getValue());
    }
}
