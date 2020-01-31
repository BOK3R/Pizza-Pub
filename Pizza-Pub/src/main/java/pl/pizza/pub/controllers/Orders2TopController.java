package pl.pizza.pub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Contains top buttons of Orders scene
 */
public class Orders2TopController {

    public static final String GET_ORDERS_FXML = "/fxml/GetOrders.fxml";
    public static final String ORDERS_1_PROCESS_FXML = "/fxml/Orders2Process.fxml";
    private Orders2Controller orders2Controller;

    @FXML
    private Button firstButton;
    @FXML
    private Button secondButton;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        firstButton.setDisable(true);
    }

    /**
     * Starts setCenter method of class Orders2Controller
     */
    public void onActionFirstButton(ActionEvent actionEvent) {
        orders2Controller.setCenter(GET_ORDERS_FXML);
    }

    /**
     * Starts setCenter method of class Orders2Controller
     */
    public void onActionSecondButton(ActionEvent actionEvent) {
        orders2Controller.setCenter(ORDERS_1_PROCESS_FXML);
    }

    /**
     * Sets controller as controller
     * @param orders2Controller controller
     */
    public void setOrders2Controller(Orders2Controller orders2Controller) {
        this.orders2Controller = orders2Controller;
    }
}
