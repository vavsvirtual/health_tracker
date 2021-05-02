import Frontend.GoalSettingPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

public class FrontEndDemoGoalSetting extends Application {
    double x,y = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        GoalSettingPane pane = new GoalSettingPane();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED );

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event ->{
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        pane.getChildren().addAll(root);
        primaryStage.setTitle(pane.getTitle());
        primaryStage.setScene(new Scene(pane, 1200, 700));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}