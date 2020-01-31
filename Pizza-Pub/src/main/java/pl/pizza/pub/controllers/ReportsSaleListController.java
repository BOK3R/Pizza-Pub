package pl.pizza.pub.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.pizza.pub.models.MenuModel;
import pl.pizza.pub.models.OrdersModel;
import pl.pizza.pub.models.SalesFx;

/**
 * Controller class of ReportsSaleList.fxml
 */
public class ReportsSaleListController {

    private MenuModel menuModel;
    private OrdersModel ordersModel;

    @FXML
    private TableView<SalesFx> menuTableView;
    @FXML
    private TableColumn<SalesFx, String> menuTableNameColumn;
    @FXML
    private TableColumn<SalesFx, Integer> menuTablePriceColumn;
    @FXML
    private TableColumn<SalesFx, String> menuTableSizeColumn;
    @FXML
    private TableColumn<SalesFx, Integer> menuTableSalesColumn;
    @FXML
    private Label numberOfRealisedSalesLabel;
    @FXML
    private Label numberOfCanceledSalesLabel;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        menuModel = new MenuModel();
        menuModel.initSalesList();

        menuTableView.setItems(menuModel.getSalesFxObservableList());
        menuTableNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        menuTablePriceColumn.setCellValueFactory(new PropertyValueFactory<SalesFx, Integer>("price"));
        menuTableSizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        menuTableSalesColumn.setCellValueFactory(new PropertyValueFactory<SalesFx, Integer>("sale"));

        ordersModel = new OrdersModel();
        numberOfRealisedSalesLabel.setText(ordersModel.getNumberOfRealisedSales());
        numberOfCanceledSalesLabel.setText(ordersModel.getNumberOfCanceledSales());
    }
}
