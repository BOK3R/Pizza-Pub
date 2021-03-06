package pl.pizza.pub.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.pizza.pub.models.EmployeesModel;
import pl.pizza.pub.utils.Alerts;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller class for LoginFormForOrders.fxml;
 */
public class LoginFormForOrdersController {

    private EmployeesModel employeesModel;
    private Stage dialogStage = new Stage();
    private int whoami = 1;

    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private ToggleButton firstToggleButton;
    @FXML
    private ToggleButton secondToggleButton;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        activeButtons();
        limitedTextField();
    }

    /**
     * Determines when buttons are disabled
     */
    private void activeButtons() {
        firstToggleButton.setSelected(true);

        loginButton.disableProperty().bind(
                loginTextField.textProperty().isEmpty()
                        .or(passwordTextField.textProperty().isEmpty())
        );
    }

    /**
     * Sets limits for TextField
     */
    private void limitedTextField() {
        loginTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (loginTextField.getText().length() > 30) {
                    String s = loginTextField.getText().substring(0, 30);
                    loginTextField.setText(s);
                }
            }
        });

        passwordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (passwordTextField.getText().length() > 30) {
                    String s = passwordTextField.getText().substring(0, 30);
                    passwordTextField.setText(s);
                }
            }
        });
    }

    /**
     * Starts login method of class EmployeesModel and open new scene
     */
    public void onActionLogin(ActionEvent actionEvent) {
        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        employeesModel = new EmployeesModel();
        int ans = employeesModel.login(login, password);

        if (ans == 1 && ans == whoami) {
            Node node = (Node) actionEvent.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Orders1.fxml"));

            ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
            loader.setResources(bundle);

            try {
                BorderPane menuBorderPane = loader.load();
                Scene scene = new Scene(menuBorderPane);
                dialogStage.setTitle(bundle.getString("Main.titleApplication"));
                dialogStage.setScene(scene);
                dialogStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ans == 2 && ans == whoami) {
            Node node = (Node) actionEvent.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Orders2.fxml"));

            ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
            loader.setResources(bundle);

            try {
                BorderPane menuBorderPane = loader.load();
                Scene scene = new Scene(menuBorderPane);
                dialogStage.setTitle(bundle.getString("Main.titleApplication"));
                dialogStage.setScene(scene);
                dialogStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (ans == -1) {
            Alerts.incorrectLoginAlert();
            loginTextField.clear();
            passwordTextField.clear();
        } else if (ans == 3 || ans == 4) {
            Alerts.noPermissionAlert();
            loginTextField.clear();
            passwordTextField.clear();
        } else {
            Alerts.unknowLoginError();
            loginTextField.clear();
            passwordTextField.clear();
        }
    }

    /**
     * Sets whoami value as 1
     */
    public void onActionFirstToggleButton(ActionEvent actionEvent) {
        whoami = 1;
    }

    /**
     * Sets whoami value as 2
     */
    public void onActionSecondToggleButton(ActionEvent actionEvent) {
        whoami = 2;
    }
}
