package pl.pizza.pub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Contains top buttons of Orders scene
 */
public class Orders1TopController {

    public static final String GET_ORDERS_FXML = "/fxml/GetOrders.fxml";
    public static final String ORDERS_1_PROCESS_FXML = "/fxml/Orders1Process.fxml";
    private Orders1Controller orders1Controller;

    @FXML
    private Button firstButton;
    @FXML
    private Button secondButton;

    /**
     * Starts setCenter method of class Orders1Controller
     */
    public void onActionFirstButton(ActionEvent actionEvent) {
        orders1Controller.setCenter(GET_ORDERS_FXML);
    }

    /**
     * Starts setCenter method of class Orders1Controller
     */
    public void onActionSecondButton(ActionEvent actionEvent) {
        orders1Controller.setCenter(ORDERS_1_PROCESS_FXML);
    }

    /**
     * Sets controller as controller
     * @param orders1Controller controller
     */
    public void setOrders1Controller(Orders1Controller orders1Controller) {
        this.orders1Controller = orders1Controller;
    }


}
