package sample;

import javafx.scene.Group;
import javafx.scene.control.TableView;
import javafx.scene.shape.Circle;

import java.util.concurrent.ThreadLocalRandom;

public class OfflineGenerator {

    public static void offlineGeneration(Group group, TableView<Coordinates> tableView) {
        int x = ThreadLocalRandom.current().nextInt(Constants.getGeneratorLowestLimit(),Constants.getGeneratorHighestLimit());
        int y = ThreadLocalRandom.current().nextInt(Constants.getGeneratorLowestLimit(),Constants.getGeneratorHighestLimit());
        group.getChildren().add(new Circle(x, y,Constants.getTargetSpotRadius(), Constants.getTargetSpotColor()));
        tableView.getItems().add(new Coordinates(x,y));
    }
}
