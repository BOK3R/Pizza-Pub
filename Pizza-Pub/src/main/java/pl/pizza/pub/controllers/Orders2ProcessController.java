package pl.pizza.pub.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.pizza.pub.models.MenuFx;
import pl.pizza.pub.models.OrdersFx;
import pl.pizza.pub.models.OrdersModel;
import pl.pizza.pub.utils.Alerts;
import pl.pizza.pub.utils.ApplicationException;

/**
 * Controller class of Order2Process.fxml
 */
public class Orders2ProcessController {

    private OrdersModel ordersModel;
    private OrdersFx currentOrder;

    @FXML
    private TableView<OrdersFx> ordersTableView;
    @FXML
    private TableColumn<OrdersFx, String> ordersTableNameColumn;
    @FXML
    private TableColumn<OrdersFx, String> ordersTableStatusColumn;
    @FXML
    private TableColumn<OrdersFx, OrdersFx> ordersTableDetailsColumn;
    @FXML
    private Button refreshButton;
    @FXML
    private Label currentOrderLabel;
    @FXML
    private TableView<MenuFx> currentOrderTableView;
    @FXML
    private TableColumn<MenuFx, String> currentOrderTableNameColumn;
    @FXML
    private TableColumn<MenuFx, String> currentOrderTableSizeColumn;
    @FXML
    private TableColumn<MenuFx, Integer> currentOrderTablePriceColumn;
    @FXML
    private Button currentOrderFirstButton;
    @FXML
    private Button currentOrderSecondButton;
    @FXML
    private Button currentOrderThirdButton;
    @FXML
    private Button currentOrderFourthButton;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        ordersModel = new OrdersModel();
        currentOrder = new OrdersFx();
        ordersModel.initOrdersList();

        ordersTableView.setItems(ordersModel.getOrdersFxObservableList());
        ordersTableNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ordersTableStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ordersTableDetailsColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        ordersTableDetailsColumn.setCellFactory(param -> new TableCell<OrdersFx, OrdersFx>() {
            Button button = createDetalisButton();

            @Override
            protected void updateItem(OrdersFx item, boolean empty) {
                super.updateItem(item, empty);

                if(empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            ordersModel.getCurrentOrder(item.getIdOrder());
                            currentOrderLabel.setText(item.getName());
                            currentOrder = item;
                            activeButtons();
                        } catch (ApplicationException e) {
                            Alerts.getOrderError(e.getMessage());
                        }
                    });
                }
            }
        });

        currentOrderTableView.setItems(ordersModel.getMenuFxObservableCurrentOrderList());
        currentOrderTableNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        currentOrderTableSizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        currentOrderTablePriceColumn.setCellValueFactory(new PropertyValueFactory<MenuFx, Integer>("price"));

        activeButtons();
    }

    /**
     * Determines when buttons are disabled
     */
    private void activeButtons() {
        currentOrderFirstButton.disableProperty().bind(Bindings.notEqual("oczekujące", currentOrder.statusProperty()));
        currentOrderSecondButton.disableProperty().bind(Bindings.notEqual("w realizacji", currentOrder.statusProperty()));

        currentOrderThirdButton.setDisable(true);
        currentOrderFourthButton.setDisable(true);
    }

    /**
     * Creates new buttons for "more" in tableview
     * @return button
     */
    private Button createDetalisButton() {
        Button button = new Button();
        button.setText("Więcej");

        return button;
    }

    /**
     * To refresh tableview
     */
    public void onActionRefreshButton(ActionEvent actionEvent) {
        ordersModel.initOrdersList();
    }

    /**
     * Starts updateStatusToRealising method of class OrdersModel
     */
    public void onActionCurrentOrderFirstButton(ActionEvent actionEvent) {
        try {
            ordersModel.updateStatusToRealising(currentOrder.getIdOrder());
            currentOrder.setStatus("w realizacji");
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }
    }

    /**
     * Starts updateStatusToReady method of class OrdersModel
     */
    public void onActionCurrentOrderSecondButton(ActionEvent actionEvent) {
        try {
            ordersModel.updateStatusToReady(currentOrder.getIdOrder());
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }

        currentOrder = new OrdersFx();
        currentOrderLabel.setText("");
    }

    /**
     * Starts updateStatusToCompleted method of class OrdersModel
     */
    public void onActionCurrentOrderThirdButton(ActionEvent actionEvent) {
        try {
            ordersModel.updateStatusToCompleted(currentOrder.getIdOrder());
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }

        currentOrder = new OrdersFx();
        currentOrderLabel.setText("");
    }

    /**
     * Starts updateStatusToCanceled method of class OrdersModel
     */
    public void onActionCurrentOrderFourthButton(ActionEvent actionEvent) {
        try {
            ordersModel.updateStatusToCanceled(currentOrder.getIdOrder());
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }

        currentOrder = new OrdersFx();
        currentOrderLabel.setText("");
    }
}
