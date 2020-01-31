package pl.pizza.pub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Contains top buttons of Reports scene
 */
public class ReportsTopController {

    public static final String REPORTS_SALE_LIST_FXML = "/fxml/ReportsSaleList.fxml";
    public static final String REPORTS_ORDERS_LIST_FXML = "/fxml/ReportsOrdersList.fxml";
    private ReportsController reportsController;

    @FXML
    private Button firstButton;
    @FXML
    private Button secondButton;

    /**
     * Starts setCenter method of class ReportsController
     */
    public void onActionFirstButton(ActionEvent actionEvent) {
        reportsController.setCenter(REPORTS_SALE_LIST_FXML);
    }

    /**
     * Starts setCenter method of class ReportsController
     */
    public void onActionSecondButton(ActionEvent actionEvent) {
        reportsController.setCenter(REPORTS_ORDERS_LIST_FXML);
    }

    /**
     * Sets controller as controller
     * @param reportsController controller
     */
    public void setReportsController(ReportsController reportsController) {
        this.reportsController = reportsController;
    }
}
