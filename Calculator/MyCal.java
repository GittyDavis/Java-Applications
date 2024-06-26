import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
/**
 * @author Gitty Davis
 * (Tova Gittel Lazarovitch)
 * ID: 212878060
*/

public class MyCal extends Application
{
    public void start(Stage stage)
    {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MyCal.fxml")));
            Scene scene = new Scene(root); // attach scene graph to scene
            stage.setTitle("Calender"); // displayed in window's title bar
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

