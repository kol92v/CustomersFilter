package view.viewFX.elementsGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import view.View;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class AdderBases implements View {

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
                        File.separator + "addNewBases.fxml")));
        primaryStage.setTitle("Add new bases");
        primaryStage.setScene(new Scene(root, 276, 333));
        primaryStage.showAndWait();
    }
}
