import controller.MovieAppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the Movie Database application.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML layout and set up the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/MovieApp.fxml"));
        Parent root = loader.load();
        MovieAppController controller = loader.getController();

        // Set up the primary stage
        primaryStage.setTitle("Movie Database App");
        primaryStage.setScene(new Scene(root, 670, 390));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
