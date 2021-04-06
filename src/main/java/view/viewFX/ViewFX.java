package view.viewFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import mygroup.controllers.MainController;
import view.Request;
import view.View;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;

public class ViewFX extends Application implements View {

    @Override
    public void runView() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL resource = Thread.currentThread().getContextClassLoader().getResource("generalWindow.fxml");
        Parent root = FXMLLoader
                .load(resource);

        primaryStage.setTitle("Update file filter");
        primaryStage.setScene(new Scene(root, 700, 600));


        primaryStage.show();
    }





}
