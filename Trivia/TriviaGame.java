import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class TriviaGame extends Application
{
    public void start(Stage stage)
    {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TriviaGame.fxml")));
            Scene scene = new Scene(root); // attach scene graph to scene
            stage.setTitle("Trivia game"); // displayed in window's title bar
            stage.setScene(scene); // attach scene to stage
            stage.show(); // display the stage
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
