package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class Layout {

    private final TableView<Coordinates> tableView;
    private final Group group;
    private final ImageView imageView;
    private final VBox vbox;
    private final TextArea textArea;

    public Layout(TableView<Coordinates> tableView, Group group, ImageView imageView, VBox vbox, TextArea textArea) {
        this.tableView = tableView;
        this.group = group;
        this.imageView = imageView;
        this.vbox = vbox;
        this.textArea = textArea;
    }

    void createAndStyleButtons() {
        Button startListeningButton = new Button(Constants.getServerButtonName());
        Button stopButton = new Button(Constants.getStopButtonName());
        Button offlineButton = new Button(Constants.getOfflineButtonName());


        startListeningButton.setMaxSize(Constants.getButtonWidth(), Constants.getButtonHeight());
        stopButton.setMaxSize(Constants.getButtonWidth(), Constants.getButtonHeight());
        offlineButton.setMaxSize(Constants.getButtonWidth(), Constants.getButtonHeight());

        vbox.getChildren().addAll(offlineButton, startListeningButton, stopButton, textArea);

        vbox.setPadding(new Insets(Constants.getInsets(), Constants.getInsets(), Constants.getInsets(), Constants.getInsets()));
        vbox.setSpacing(Constants.getBoxSpacing());

        setupButtonActionEvents(startListeningButton, stopButton, offlineButton);
    }

    void loadAndSetImage() {
        Image image = new Image(getClass().getResourceAsStream(Constants.getFileLocation()));
        imageView.setImage(image);
        imageView.setFitHeight(Constants.getImageHeight());
        imageView.setFitWidth(Constants.getImageWidth());
        imageView.setPreserveRatio(true);
    }

    void configureTable() {
        TableColumn<Coordinates, Void> column1 = new TableColumn<>(Constants.getTableColumnIndex());
        column1.setCellFactory(col -> {
            TableCell<Coordinates, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null;
                } else {
                    return Integer.toString(cell.getIndex());
                }
            }, cell.emptyProperty(), cell.indexProperty()));
            return cell;
        });

        TableColumn<Coordinates, Integer> column2 = new TableColumn<>(Constants.getTableColumnFirstVariableColumn());
        column2.setCellValueFactory(new PropertyValueFactory<>(Constants.getCoordinateClassPropertyXName()));

        TableColumn<Coordinates, Integer> column3 = new TableColumn<>(Constants.getTableColumnSecondVariableColumn());
        column3.setCellValueFactory(new PropertyValueFactory<>(Constants.getCoordinateClassPropertyYName()));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
    }

    private void setupButtonActionEvents(Button startListeningButton, Button stopButton, Button offlineButton) {

        Timeline offlineTimeLine = new Timeline(new KeyFrame(Duration.millis(Constants.getOfflineGenerationDuration()), (ActionEvent event) -> OfflineGenerator.offlineGeneration(group, tableView)));

        offlineTimeLine.setCycleCount(Timeline.INDEFINITE);

        ServerListener serverListener = new ServerListener(tableView, group, textArea);

        EmergencyStop emergencyStop = new EmergencyStop(textArea);

        startListeningButton.setOnAction(actionEvent -> {
            offlineTimeLine.stop();
            clearGroupsAndTables();
            serverListener.requestStart();
            startThread(serverListener, "Server listener thread");
        });

        stopButton.setOnAction(actionEvent -> {
            if(offlineTimeLine.getStatus() != Animation.Status.RUNNING) {
                serverListener.requestStop();
                startThread(emergencyStop, "Emergency stop thread");
            }
            else {
                offlineTimeLine.stop();
                textArea.setText("Stopped generating offline coordinates");
            }
        });

        offlineButton.setOnAction(actionEvent -> {
            serverListener.requestStop();
            offlineTimeLine.play();
            clearGroupsAndTables();
            textArea.setText("Generating random offline coordinates");
        });
    }

    void configureTextArea() {
        textArea.setPrefSize(Constants.getTextAreaWidth(), Constants.getTextAreaHeight());
        textArea.setWrapText(true);
    }

    void startThread(Runnable runnable, String threadName) {
        Thread t = new Thread(runnable, threadName);
        t.setDaemon(true);
        t.start();
    }

    void clearGroupsAndTables() {
        tableView.getItems().clear();
        group.getChildren().clear();
        group.getChildren().add(imageView);
    }
}
