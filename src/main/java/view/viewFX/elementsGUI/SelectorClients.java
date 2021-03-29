package view.viewFX.elementsGUI;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import mygroup.controllers.MainController;
import view.Request;
import view.TypeMessage;
import view.dtoVw.ClientVw;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectorClients {
    private final MainController mainController = new MainController();
    @Getter @Setter
    private TextField pathSourceFolder;
    @Getter @Setter
    private TextField pathTargetFolder;
    @Getter @Setter
    private VBox vBoxClients;
    @Getter @Setter
    private TextField dateFromGeneral;
    @Getter @Setter
    private TextField dateToGeneral;

    public void startPackage() {
        List<ClientVw> clientVwList = getUpdateClientList();
        mainController.processingRequest(Request.builder()
                .typeController(Arrays.asList(Request.TypeController.PckDirectory, Request.TypeController.FinderFolderDate))
                .typeMessage(TypeMessage.StartToPackage)
                .sourceFolder(Paths.get(pathSourceFolder.textProperty().getValue()))
                .targetFolder(Paths.get(pathTargetFolder.textProperty().getValue()))
                .clientList(clientVwList)
                .build());
    }

    public List<ClientVw> getUpdateClientList() {
        List<ClientVw> clientVwList = new ArrayList<>();
        vBoxClients.getChildren().stream().filter(node -> isSelected((BorderPane)node))
                .forEach(node -> clientVwList.add(getClientForPack((BorderPane) node)));
        return clientVwList;
    }

    private boolean isSelected(BorderPane client) {
        SplitPane splitPane = (SplitPane) client.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(0);
        CheckBox checkBox = (CheckBox) anchorPane.getChildren().get(0);
        return checkBox.isSelected();
    }

    private ClientVw getClientForPack(BorderPane bpClient) {
        ClientVw clientVw = new ClientVw(getClientName(bpClient));
        if (isDateFromEmpty(bpClient)) {
            clientVw.setDateFrom(getMainDateFrom());
        } else {
            clientVw.setDateFrom(getClientDateFrom(bpClient));
        }
        if (isDateToEmpty(bpClient)) {
            clientVw.setDateTo(getMainDateTo());
        } else {
            clientVw.setDateTo(getClientDateTo(bpClient));
        }
        return clientVw;
    }

    private String getClientName(BorderPane client) {
        SplitPane splitPane = (SplitPane) client.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(1);
        Hyperlink hypClientName = (Hyperlink) anchorPane.getChildren().get(0);
        return hypClientName.textProperty().getValue();
    }

    private boolean isDateFromEmpty(BorderPane bpClient) {
        SplitPane splitPane = (SplitPane) bpClient.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(2);
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        String strDate = textField.getText();
        return strDate == null || strDate.isEmpty();
    }

    private boolean isDateToEmpty(BorderPane bpClient) {
        SplitPane splitPane = (SplitPane) bpClient.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(3);
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        String strDate = textField.getText();
        return strDate == null || strDate.isEmpty();
    }

    private LocalDate getClientDateFrom(BorderPane bpClient) {
        SplitPane splitPane = (SplitPane) bpClient.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(2);
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        return LocalDate.parse(textField.getText());
    }

    private LocalDate getClientDateTo(BorderPane bpClient) {
        SplitPane splitPane = (SplitPane) bpClient.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(3);
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        return LocalDate.parse(textField.getText());
    }

    private LocalDate getMainDateFrom() {
        return LocalDate.parse(dateFromGeneral.getText());
    }

    private LocalDate getMainDateTo() {
        return LocalDate.parse(dateToGeneral.getText());
    }




}
