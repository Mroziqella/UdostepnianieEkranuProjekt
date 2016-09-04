package pl.mroziqella.GUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Main.primaryStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view.fxml"));
        primaryStage.setTitle("Provider");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);

    }
}
