package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle(Constants.getTitle());

        ImageView imageView = new ImageView();
        VBox buttonBox = new VBox();
        TableView<Coordinates> tableView = new TableView<>();
        BorderPane borderPane = new BorderPane();
        Group group = new Group(imageView);
        TextArea textArea = new TextArea();

        // Main Layout configuration
        Layout layout =  new Layout(tableView,group, imageView, buttonBox, textArea);

        layout.createAndStyleButtons();

        layout.loadAndSetImage();

        layout.configureTable();

        layout.configureTextArea();

        borderPane.setRight(tableView);
        borderPane.setLeft(buttonBox);
        borderPane.setCenter(group);

        Scene scene = new Scene(borderPane, Constants.getSceneWidth(),Constants.getSceneHeight());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



