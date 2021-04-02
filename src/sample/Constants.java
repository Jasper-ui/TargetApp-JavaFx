package sample;

import javafx.scene.paint.Color;

public class Constants {

    private static final String title = "Mock Target Application";
    private static final int sceneWidth = 900;
    private static final int sceneHeight = 400;
    private static final String serverButtonName = "Start Listening";
    private static final String stopButtonName = "Emergency Stop";
    private static final String offlineButtonName = "Offline Generation";
    private static final int buttonWidth = 150;
    private static final int buttonHeight = 100;
    private static final int insets = 15;


    private static final int boxSpacing = 50;
    private static final String fileLocation = "/resources/Target.jpg";
    private static final int imageWidth = 400;
    private static final int imageHeight = 400;
    private static final String tableColumnIndex = "Index";
    private static final String tableColumnFirstVariableColumn = "X";
    private static final String tableColumnSecondVariableColumn = "Y";
    private static final String coordinateClassPropertyXName = "xCoordinate";
    private static final String coordinateClassPropertyYName = "yCoordinate";
    private static final int offlineGenerationDuration = 1000;
    private static final int generatorLowestLimit = 5;
    private static final int generatorHighestLimit = 395;
    private static final int targetSpotRadius = 5;
    private static final javafx.scene.paint.Color targetSpotColor = Color.GREEN;

    private static final int serverPort = 8080;

    private static final int textAreaWidth = 150;
    private static final int textAreaHeight = 150;
    private static final String nodeApiAddress = "http://localhost:3001/javafx-api";
    private static final int serverTimeoutMillis = 10000;

    public static String getTitle() {
        return title;
    }

    public static int getSceneWidth() {
        return sceneWidth;
    }

    public static int getSceneHeight() {
        return sceneHeight;
    }

    public static String getServerButtonName() {
        return serverButtonName;
    }

    public static String getStopButtonName() {
        return stopButtonName;
    }

    public static String getOfflineButtonName() {
        return offlineButtonName;
    }

    public static int getButtonWidth() {
        return buttonWidth;
    }

    public static int getButtonHeight() {
        return buttonHeight;
    }

    public static int getBoxSpacing() {
        return boxSpacing;
    }

    public static int getInsets() {
        return insets;
    }

    public static String getFileLocation() {
        return fileLocation;
    }

    public static int getImageWidth() {
        return imageWidth;
    }

    public static int getImageHeight() {
        return imageHeight;
    }

    public static String getTableColumnIndex() {
        return tableColumnIndex;
    }

    public static String getTableColumnFirstVariableColumn() {
        return tableColumnFirstVariableColumn;
    }

    public static String getTableColumnSecondVariableColumn() {
        return tableColumnSecondVariableColumn;
    }

    public static String getCoordinateClassPropertyXName() {
        return coordinateClassPropertyXName;
    }

    public static String getCoordinateClassPropertyYName() {
        return coordinateClassPropertyYName;
    }

    public static int getOfflineGenerationDuration() {
        return offlineGenerationDuration;
    }

    public static int getGeneratorLowestLimit() {
        return generatorLowestLimit;
    }

    public static int getGeneratorHighestLimit() {
        return generatorHighestLimit;
    }

    public static int getTargetSpotRadius() {
        return targetSpotRadius;
    }

    public static javafx.scene.paint.Color getTargetSpotColor() {
        return targetSpotColor;
    }

    public static int getServerPort() {
        return serverPort;
    }


    public static double getTextAreaWidth() {
        return textAreaWidth;
    }

    public static double getTextAreaHeight() {
        return textAreaHeight;
    }

    public static String getNodeApiAddress() {
        return nodeApiAddress;
    }

    public static int getServerTimeOutMillis() {
        return serverTimeoutMillis;
    }
}
