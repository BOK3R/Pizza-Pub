package pl.pizza.pub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller class for MainMenu.fxml;
 */
public class MainMenuController {
    public static final String LOGIN_FORM_FOR_ORDERS_FXML = "/fxml/LoginFormForOrders.fxml";
    public static final String LOGIN_FORM_FOR_SERVICES_FXML = "/fxml/LoginFormForServices.fxml";
    public static final String LOGIN_FORM_FOR_REPORTS_FXML = "/fxml/LoginFormForReports.fxml";
    public static final String LOGIN_FORM_FOR_ADMIN_FXML = "/fxml/LoginFormForAdmin.fxml";
    private MainSceneController mainSceneController;

    /**
     * Initializes starting parameters of scene
     */
    @FXML
    public void initialize() {
    }

    /**
     * Sets controller as controller
     * @param mainSceneController controller
     */
    public void setMainSceneController(MainSceneController mainSceneController) {
        this.mainSceneController = mainSceneController;
    }

    /**
     * Starts setCenter method of class MainSceneController
     */
    public void onActionOrdersButton(ActionEvent actionEvent) {
        mainSceneController.setCenter(LOGIN_FORM_FOR_ORDERS_FXML);
    }

    /**
     * Starts setCenter method of class MainSceneController
     */
    public void onActionServicesButton(ActionEvent actionEvent) {
        mainSceneController.setCenter(LOGIN_FORM_FOR_SERVICES_FXML);
    }

    /**
     * Starts setCenter method of class MainSceneController
     */
    public void onActionReportsButton(ActionEvent actionEvent) {
        mainSceneController.setCenter(LOGIN_FORM_FOR_REPORTS_FXML);
    }

    /**
     * Starts setCenter method of class MainSceneController
     */
    public void onActionAdminButton(ActionEvent actionEvent) {
        mainSceneController.setCenter(LOGIN_FORM_FOR_ADMIN_FXML);
    }
}
