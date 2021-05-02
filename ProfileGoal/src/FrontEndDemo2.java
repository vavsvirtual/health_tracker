import frontend.GoalSettingPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrontEndDemo2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GoalSettingPane root =  new GoalSettingPane();

        stage.setScene(new Scene(root));
        stage.setTitle(root.getTitle());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
