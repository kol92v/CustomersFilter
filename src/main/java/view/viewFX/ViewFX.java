package view.viewFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
                                File.separator + "sample.fxml")));
        for (Node node : root.getChildrenUnmodifiable()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                Pane pane1 = (Pane) hBox.getChildren().get(0);
                ScrollPane scrollPane = (ScrollPane) pane1.getChildren().get(0);
                VBox vBox = (VBox) scrollPane.getContent();
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
                vBox.getChildren().add(new Text("hi"));
            }
        }

        primaryStage.setTitle("Update file filter");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();

    }

    public static void main(String[] args) throws MalformedURLException {
        launch(args);

    }
}
