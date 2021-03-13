package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;

public class ViewFX extends Application implements View {
    @Override
    public void start(Stage primaryStage) throws Exception {

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root = FXMLLoader.load(new URL("file:D:\\portfolio\\CustomersFilter\\target\\classes\\sample.fxml"));

        primaryStage.setTitle("Update file filter");
        primaryStage.setScene(new Scene(root, 1000, 900));
        primaryStage.show();
    }

    public static void main(String[] args) throws MalformedURLException {
        launch(args);

    }
}
