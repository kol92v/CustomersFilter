package view.viewFX.elementsGUI;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import mygroup.controllers.MainController;
import view.Request;
import view.Response;
import view.TypeMessage;
import view.dtoVw.ClientVw;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;


public class TableClients {
    @Getter @Setter
    private VBox vBoxClients;

    private final URL clientInTableFXML = new URL("file:" + Paths.get(System.getProperty("user.dir") +
            File.separator +  "target" + File.separator + "classes" +
            File.separator + "clientInTable.fxml"));

    private final MainController mainController = new MainController();

    private static TableClients instance;
    private TableClients() throws MalformedURLException {}
    @SneakyThrows
    public static TableClients getInstance() {
        if (instance == null) {
            synchronized (TableClients.class) {
                if (instance == null) instance = new TableClients();
            }
        }
        return instance;
    }

    public void init() {
        Response getClients = mainController.processingRequest(Request.builder().typeMessage(TypeMessage.GetClients)
                .typeController(Collections.singletonList(Request.TypeController.CRUDProperty))
                .build());
        addClients(getClients.getClientVwList());
    }

    @SneakyThrows(IOException.class)
    private BorderPane getClientForTable() {
        return FXMLLoader.load(clientInTableFXML);
    }

    private void setClientName(BorderPane client, String clientName) {
        SplitPane splitPane = (SplitPane) client.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(1);
        Hyperlink hypClientName = (Hyperlink) anchorPane.getChildren().get(0);
        hypClientName.textProperty().setValue(clientName);
    }

    private String getClientName(BorderPane client) {
        SplitPane splitPane = (SplitPane) client.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(1);
        Hyperlink hypClientName = (Hyperlink) anchorPane.getChildren().get(0);
        return hypClientName.textProperty().getValue();
    }

    private boolean isSelected(BorderPane client) {
        SplitPane splitPane = (SplitPane) client.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(0);
        CheckBox checkBox = (CheckBox) anchorPane.getChildren().get(0);
        return checkBox.isSelected();
    }

    public void addClients(List<ClientVw> clientVwList) {
        for (ClientVw clientVw : clientVwList) {
            BorderPane bpClient = getClientForTable();
            setClientName(bpClient, clientVw.getName());
            vBoxClients.getChildren().add(bpClient);
        }
    }

    public List<ClientVw> removeClients() {
        List<ClientVw> clientVwList = new ArrayList<>();
        Iterator<Node> nodeIterator = vBoxClients.getChildren().iterator();
        while (nodeIterator.hasNext()) {
            BorderPane bpClient = (BorderPane) nodeIterator.next();
            if (isSelected(bpClient)) {
                nodeIterator.remove();
                clientVwList.add(new ClientVw(getClientName(bpClient)));
            }
        }
        return clientVwList;
    }


}
