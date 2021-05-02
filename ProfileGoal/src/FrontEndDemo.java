import frontend.ProfilePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrontEndDemo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ProfilePane root = new ProfilePane();
 
        stage.setScene(new Scene(root));
        stage.setTitle(root.getTitle());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
