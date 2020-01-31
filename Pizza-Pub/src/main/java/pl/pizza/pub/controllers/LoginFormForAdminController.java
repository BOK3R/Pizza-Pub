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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.pizza.pub.models.EmployeesModel;
import pl.pizza.pub.utils.Alerts;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller class for LoginFormForAdmin.fxml;
 */
public class LoginFormForAdminController {

    public static final String ADMIN_FXML = "/fxml/Admin.fxml";
    private EmployeesModel employeesModel;
    private Stage dialogStage = new Stage();

    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;

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

        if (ans == 4) {
            Node node = (Node) actionEvent.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(ADMIN_FXML));

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
        } else if (ans == -1) {
            Alerts.incorrectLoginAlert();
            loginTextField.clear();
            passwordTextField.clear();
        } else if (ans == 1 || ans == 2 || ans == 3) {
            Alerts.noPermissionAlert();
            loginTextField.clear();
            passwordTextField.clear();
        } else {
            Alerts.unknowLoginError();
            loginTextField.clear();
            passwordTextField.clear();
        }
    }
}
