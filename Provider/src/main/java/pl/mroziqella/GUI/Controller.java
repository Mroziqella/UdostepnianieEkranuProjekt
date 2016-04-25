package pl.mroziqella.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import pl.mroziqella.impl.Provider;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TextField password;
    @FXML
    private TextField login;
    @FXML
    private Label display;
    @FXML
    private Button connectButton;
    @FXML
    private RadioButton radioButtonHeight;
    @FXML
    private RadioButton radioButtonLow;
    @FXML
    private RadioButton radioButtonNormal;

    @Override
    public void initialize(final URL location, ResourceBundle resources) {
        final Provider provider = new Provider("127.0.0.1", "server", 2000);
        connectButton.setOnAction(new ConnectButtonClick(provider));
        radioButtonHeight.setOnAction(new RadioButtonClickQuality(provider, 100));
        radioButtonNormal.setOnAction(new RadioButtonClickQuality(provider, 70));
        radioButtonLow.setOnAction(new RadioButtonClickQuality(provider, 30));
        Main.getPrimaryStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                provider.stop();

           }
        });

    }

    private class RadioButtonClickQuality implements EventHandler<ActionEvent> {
        private int quality;
        private Provider provider;

        public RadioButtonClickQuality(Provider provider, int quality) {
            super();
            this.provider = provider;
            this.quality = quality;

        }

        @Override
        public void handle(ActionEvent event) {
            provider.getImageScreenShot().setQuality(quality);
        }
    }

    private class ConnectButtonClick implements EventHandler<ActionEvent> {
        private Provider provider;

        public ConnectButtonClick(Provider provider) {
            this.provider = provider;
        }

        @Override
        public void handle(ActionEvent event) {
            try {
                provider.connect();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
            try {
                if (provider.getRmi().isUser(login.getText(), password.getText())) {
                    if (!display.getText().equals("Połączono")) {
                        display.setText("Połączono");
                        provider.setLogin(login.getText());
                        new Thread(provider).start();
                        connectButton.setText("Zatrzymaj");
                    } else {
                        connectButton.setText("Połącz");
                        display.setText("Niepołączono");
                        provider.stop();
                    }
                } else {
                    display.setText("Błędny login");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


    }
}
