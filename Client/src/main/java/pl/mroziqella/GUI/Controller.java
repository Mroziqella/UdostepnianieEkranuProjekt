package pl.mroziqella.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.mroziqella.RMIConnect.ThreadImage;
import pl.mroziqella.inte.MouseInfo;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;


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


            if (clientRMI.getRmi().isUser(loginTextField.getText(), passwordTextField.getText())) {
                Thread.sleep(2000);

                if (!statusLabel.getText().equals("Połączony")) {

                    Thread threadImage = new Thread(clientRMI);
                    threadImage.start();
                    statusLabel.setText("Połączony");
                    connect.setText("Zatrzymaj");
                    displayPanel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            try {
                                clientRMI.getRmi().setMouseClick(loginTextField.getText(),new MouseInfo((int)event.getX(),(int)event.getY()));
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    clientRMI.stopThread();
                    statusLabel.setText("Niepołączony");
                    connect.setText("Połącz");
                    displayPanel.setOnMouseClicked(null);
                }

            } else {
                statusLabel.setText("Niepołączony");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clientRMI = new ThreadImage("127.0.0.1", "server", 2000, displayPanel, loginTextField.getText());
        try {
            clientRMI.connect();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }


    }
}
