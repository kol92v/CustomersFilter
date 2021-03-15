package view.viewFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class ViewFX extends Application implements View {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader
                .load(new URL("file:" + Paths.get(System.getProperty("user.dir").toString() +
                                File.separator +  "target" + File.separator + "classes" +
                                File.separator + "generalWindow.fxml")));
        primaryStage.setTitle("Update file filter");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    public static void main(String[] args) throws MalformedURLException {
        launch(args);
    }
}
