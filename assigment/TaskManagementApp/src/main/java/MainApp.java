import controller.MainViewController;
import database.DatabaseInitialiser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        DatabaseInitialiser.initialiseDatabase();
        MainApp.primaryStage = primaryStage;
        primaryStage.setTitle("Task Management App");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
        Parent root = loader.load();
        MainViewController controller = loader.getController();
        controller.setMainStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
