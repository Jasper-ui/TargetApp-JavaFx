package sample;

import javafx.scene.control.TextArea;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class EmergencyStop implements Runnable {

    private static TextArea textArea;

    public EmergencyStop(TextArea textArea) {
        EmergencyStop.textArea = textArea;
    }

    @Override
    public void run() {
        Form form = new Form();
        form.add("Stop", "true");

        try {
            ClientResource resource = new ClientResource(Constants.getNodeApiAddress());

            resource.post(form.getWebRepresentation());
            Response response = resource.getResponse();

            if (response.getStatus().isSuccess()) {
                System.out.println("Success! " + response.getStatus() + " stopping server from generating numbers");
                textArea.setText("Success! " + response.getStatus() + " stopping server from generating numbers");
            } else {
                System.out.println("ERROR! " + response.getStatus());
                textArea.setText("ERROR! " + response.getStatus());
            }
        } catch (ResourceException e) {
            e.printStackTrace();
            textArea.setText(e.getMessage());
        }
    }
}
