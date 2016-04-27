package pl.mroziqella.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import pl.mroziqella.RMIConnect.ClientRMI;
import pl.mroziqella.RMIConnect.ThreadImage;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Controller implements Initializable {

    private ThreadImage clientRMI;
    @FXML
    private ImageView displayPanel;

    @FXML
    private Label statusLabel;

    @FXML
    private Button connect;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void connectButtonClick(ActionEvent event) {
        try {
            clientRMI = new ThreadImage("127.0.0.1", "server", 2000,displayPanel);
            clientRMI.connect();

            if(clientRMI.getRmi().isUser(loginTextField.getText(), passwordTextField.getText())) {
                Thread.sleep(2000);

                if(!statusLabel.getText().equals("Połączony")){

                    Thread threadImage = new Thread(clientRMI);
                    threadImage.start();
                }
                statusLabel.setText("Połączony");
            }
            else{
                statusLabel.setText("Niepołączony");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickOnImageView(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
