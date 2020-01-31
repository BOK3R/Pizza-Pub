package pl.pizza.pub.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.pizza.pub.models.MenuFx;
import pl.pizza.pub.models.MenuModel;
import pl.pizza.pub.models.OrdersModel;
import pl.pizza.pub.utils.Alerts;
import pl.pizza.pub.utils.ApplicationException;

/**
 * Controller class for GetOrders.fxml;
 */
public class GetOrdersController {

    private MenuModel menuModel;
    private OrdersModel ordersModel;

    @FXML
    private TableView<MenuFx> menuTableView;
    @FXML
    private TableColumn<MenuFx, String> menuTableNameColumn;
    @FXML
    private TableColumn<MenuFx, Integer> menuTablePriceColumn;
    @FXML
    private TableColumn<MenuFx, String> menuTableSizeColumn;
    @FXML
    private TableColumn<MenuFx, MenuFx> menuTableAddColumn;
    @FXML
    private TableView<MenuFx> orderTableView;
    @FXML
    private TableColumn<MenuFx, String> orderTableNameColumn;
    @FXML
    private TableColumn<MenuFx, Integer> orderTablePriceColumn;
    @FXML
    private TableColumn<MenuFx, String> orderTableSizeColumn;
    @FXML
    private TableColumn<MenuFx, MenuFx> orderTableDeleteColumn;
    @FXML
    private Label costLabel;
    @FXML
    private Button clearOrderButton;
    @FXML
    private Button addOrderButton;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        ordersModel = new OrdersModel();
        menuModel = new MenuModel();
        menuModel.initMenuList();

        menuTableView.setItems(menuModel.getMenuFxObservableList());
        menuTableNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        menuTablePriceColumn.setCellValueFactory(new PropertyValueFactory<MenuFx, Integer>("price"));
        menuTableSizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        menuTableAddColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        menuTableAddColumn.setCellFactory(param -> new TableCell<MenuFx, MenuFx>() {
            Button button = createAddButton();

            @Override
            protected void updateItem(MenuFx item, boolean empty) {
                super.updateItem(item, empty);

                if(!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        ordersModel.getMenuFxObservableActualOrderList().add(item);
                        costLabel.setText(Integer.toString(ordersModel.sumPrice()));
                    });
                }
            }
        });

        orderTableView.setItems(ordersModel.getMenuFxObservableActualOrderList());
        if (ordersModel.getMenuFxObservableActualOrderList().isEmpty()) {
            orderTableView.getItems().clear();
        }
        orderTableNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        orderTablePriceColumn.setCellValueFactory(new PropertyValueFactory<MenuFx, Integer>("price"));
        orderTableSizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        orderTableDeleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        orderTableDeleteColumn.setCellFactory(param -> new TableCell<MenuFx, MenuFx>() {
            Button button = createDeleteButton();

            @Override
            protected void updateItem(MenuFx item, boolean empty) {
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                } else {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        ordersModel.getMenuFxObservableActualOrderList().remove(item);
                        costLabel.setText(Integer.toString(ordersModel.sumPrice()));
                    });
                }
            }
        });

        activeButton();

    }

    /**
     * Determines when buttons are disabled
     */
    private void activeButton() {
        addOrderButton.disableProperty().bind(new BooleanBinding() {
            {
                bind(ordersModel.getMenuFxObservableActualOrderList());
            }
            @Override
            protected boolean computeValue() {
                return ordersModel.getMenuFxObservableActualOrderList().size() < 1;
            }
        });

        clearOrderButton.disableProperty().bind(new BooleanBinding() {
            {
                bind(ordersModel.getMenuFxObservableActualOrderList());
            }
            @Override
            protected boolean computeValue() {
                return ordersModel.getMenuFxObservableActualOrderList().size() < 1;
            }
        });
    }

    /**
     * Creates new buttons for "add" in tableview
     * @return button
     */
    private Button createAddButton() {
        Button button = new Button();
        button.setText("Dodaj");

        return button;
    }

    /**
     * Creates new buttons for "remove" in tableview
     * @return button
     */
    private Button createDeleteButton() {
        Button button = new Button();
        button.setText("Usuń");

        return button;
    }

    /**
     * To clear menuFxObservableActualOrderList of class OrdersModel
     */
    public void OnActionClearOrderButton(ActionEvent actionEvent) {
        ordersModel.getMenuFxObservableActualOrderList().clear();
        costLabel.setText(Integer.toString(ordersModel.sumPrice()));
    }

    /**
     * Starts addOrder method of class OrdersModel
     */
    public void onActionAddOrderButton(ActionEvent actionEvent) {
        try {
            ordersModel.addOrder();
            costLabel.setText(Integer.toString(ordersModel.sumPrice()));
        } catch (ApplicationException e) {
            Alerts.addRecordError(e.getMessage());
        }
    }
}
