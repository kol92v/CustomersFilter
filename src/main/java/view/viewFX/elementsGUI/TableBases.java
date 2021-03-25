package view.viewFX.elementsGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import view.dtoVw.BaseVw;
import view.dtoVw.ClientVw;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TableBases {
    @Getter @Setter
    private VBox vBoxBases;
    @Getter @Setter
    private Label clientNameBaseList;


    private final URL baseInTableFXML = new URL("file:" + Paths.get(System.getProperty("user.dir") +
            File.separator +  "target" + File.separator + "classes" +
            File.separator + "baseInTable.fxml"));

    private static TableBases instance;
    private TableBases() throws MalformedURLException {}
    @SneakyThrows
    public static TableBases getInstance() {
        if (instance == null) {
            synchronized (TableBases.class) {
                if (instance == null) instance = new TableBases();
            }
        }
        return instance;
    }

    @SneakyThrows(IOException.class)
    private BorderPane getBaseForTable() {
        return FXMLLoader.load(baseInTableFXML);
    }

    private void setBaseName(BorderPane base, String baseName) {
        SplitPane splitPane = (SplitPane) base.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(0);
        Label label = (Label) anchorPane.getChildren().get(0);
        label.textProperty().setValue(baseName);
    }

    private String getBaseName(BorderPane base) {
        SplitPane splitPane = (SplitPane) base.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(0);
        Label label = (Label) anchorPane.getChildren().get(0);
        return label.textProperty().getValue();
    }

    private boolean isSelected(BorderPane base) {
        SplitPane splitPane = (SplitPane) base.getChildren().get(0);
        AnchorPane anchorPane = (AnchorPane) splitPane.getItems().get(1);
        CheckBox checkBox = (CheckBox) anchorPane.getChildren().get(0);
        return checkBox.isSelected();
    }

    public void addBases(List<ClientVw> clientVwList) {
        clientVwList.forEach(clientVw -> {
            for (BaseVw baseVw : clientVw.getBaseVwList()) {
                BorderPane bpBase = getBaseForTable();
                setBaseName(bpBase, baseVw.getName());
                vBoxBases.getChildren().add(bpBase);
            }
            setClientName(clientVw.getName());
        });
    }

    public List<ClientVw> removeBases() {
        ClientVw clientVw = new ClientVw(clientNameBaseList.textProperty().getValue());
        Iterator<Node> nodeIterator = vBoxBases.getChildren().iterator();
        while (nodeIterator.hasNext()) {
            BorderPane bpBase = (BorderPane) nodeIterator.next();
            if (isSelected(bpBase)) {
                nodeIterator.remove();
                clientVw.getBaseVwList().add(new BaseVw(getBaseName(bpBase)));
            }
        }
        return Collections.singletonList(clientVw);
    }

    public void clearBases() {
        vBoxBases.getChildren().clear();
    }

    private void setClientName(String name) {
        clientNameBaseList.textProperty().setValue(name);
    }
}
