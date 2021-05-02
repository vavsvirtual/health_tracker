import Frontend.GoalSettingPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrontEndDemoGoal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GoalSettingPane root =  new GoalSettingPane();

        stage.setScene(new Scene(root, 1200, 700));
        stage.setTitle(root.getTitle());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

