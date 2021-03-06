import Frontend.GroupsPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;


public class FrontEndDemoGroups extends Application {
    double x, y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridPane = new GridPane();

        GroupsPane pane = new GroupsPane();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        //pane.getChildren().addAll(root);

        gridPane.getChildren().addAll(pane, root);

        primaryStage.setScene(new Scene(gridPane, 1200, 700));

        primaryStage.setTitle(pane.getTitle());
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

