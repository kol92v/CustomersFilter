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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;

public class ViewFX extends Application implements View {
    MainController controller = new MainController();

    @Override
    public void runView() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        method();
//        Thread.sleep(5000);
//        System.out.print("start: ");
//        System.out.println(Paths.get(System.getProperty("user.dir")));
//        System.out.print("path to class: ");
//
//        File currentClass = new File(URLDecoder.decode(ViewFX.class
//                .getProtectionDomain()
//                .getCodeSource()
//                .getLocation()
//                .getPath(), "UTF-8"));
//        String classDirectory = currentClass.getParent();
//        System.out.println(classDirectory);
        Parent root = FXMLLoader
                .load(new URL("file:" + Paths.get(System.getProperty("user.dir") +
                                File.separator +  "target" + File.separator + "classes" +
                                File.separator + "generalWindow.fxml")));
        primaryStage.setTitle("Update file filter");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    @SneakyThrows
    private void method() {
        BufferedInputStream bis = new BufferedInputStream(
        getClass().getClassLoader().getResourceAsStream("generalWindow.fxml"));
        Path tempFXML = Files.createTempFile("fxml", ".fxml");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFXML.toFile()));
        while(bis.available() > 0) {
            bos.write(bis.read());
        }
        System.out.println(tempFXML);

    }



}
