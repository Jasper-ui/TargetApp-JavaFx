package sample;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener implements Runnable {

    private static TableView<Coordinate> tableView;
    private static Group group;
    private static TextArea textArea;

    private boolean stopRequested = false;


    public ServerListener(TableView<Coordinate> tableView, Group group, TextArea textArea) {
        ServerListener.tableView = tableView;
        ServerListener.group = group;
        ServerListener.textArea = textArea;
    }

    public synchronized void requestStop() {
        this.stopRequested = true;
    }

    public synchronized void requestStart() {
        this.stopRequested = false;
    }

    public synchronized boolean isStopRequested() {
        return this.stopRequested;
    }

    //Not needed but useful for testing
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        ServerSocket server;
        Socket client;
        InputStream input;

        try {
            server = new ServerSocket(Constants.getServerPort());
            server.setSoTimeout(Constants.getServerTimeOutMillis());
            server.setReuseAddress(true);
            System.out.println("Listening on port " + Constants.getServerPort());
            textArea.setText("Listening on port " + Constants.getServerPort());

            while (!isStopRequested()) {

                try {
                    client = server.accept();
                    input = client.getInputStream();
                    InputStream is = new ByteArrayInputStream(input.readAllBytes());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    JSONTokener coordinateObject = new JSONTokener(bufferedReader);

                    try {
                        JSONObject json = new JSONObject(coordinateObject);

                        Platform.runLater(() -> {
                            textArea.setText("Received body:" + json);
                            if (json.isNull("message")) {
                                group.getChildren().add(new Circle(json.getInt(Constants.getCoordinateClassPropertyXName()), json.getInt(Constants.getCoordinateClassPropertyYName()), Constants.getTargetSpotRadius(), Constants.getTargetSpotColor()));
                                tableView.getItems().add(new Coordinate(json.getInt(Constants.getCoordinateClassPropertyXName()), json.getInt(Constants.getCoordinateClassPropertyYName())));
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    client.close();

                } catch (java.net.SocketTimeoutException e) {
                    e.printStackTrace();
                    textArea.setText("Timeout exception, server closed. Press the 'Start Listening' button again to listen on port 8080");
                    server.close();
                }
            }
            server.close();

        } catch (Exception e) {
            e.printStackTrace();
            textArea.setText(e.getMessage());
        }
    }
}
