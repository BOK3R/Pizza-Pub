package pl.pizza.pub.controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import pl.pizza.pub.models.MenuFx;
import pl.pizza.pub.models.MenuModel;
import pl.pizza.pub.models.SizeFx;
import pl.pizza.pub.models.SizeModel;
import pl.pizza.pub.utils.Alerts;
import pl.pizza.pub.utils.ApplicationException;
import pl.pizza.pub.utils.HibernateUtil;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller class for Menu.fxml;
 */
public class MenuController {

    public static final String MAIN_SCENE_FXML = "/fxml/MainScene.fxml";
    private MenuModel menuModel;
    private SizeModel sizeModel;
    private SizeFx sizeFx;
    private String newSize;

    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<MenuFx> menuTableView;
    @FXML
    private TableColumn<MenuFx, String> nameColumn;
    @FXML
    private TableColumn<MenuFx, Integer> priceColumn;
    @FXML
    private TableColumn<MenuFx, String> sizeColumn;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ComboBox<SizeFx> sizeComboBox;
    @FXML
    private Button addButton;
    @FXML
    private TextField newSizeTextField;
    @FXML
    private ComboBox newSizeComboBox;
    @FXML
    private Button newSizeAddButton;
    @FXML
    private VBox editVBox;
    @FXML
    private ComboBox<SizeFx> editSizeComboBox;
    @FXML
    private Button editCancelButton;
    @FXML
    private Button editButton;
    @FXML
    private MenuItem menuItem;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        menuModel = new MenuModel();
        menuModel.initMenuList();

        menuTableView.setItems(menuModel.getMenuFxObservableList());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(new PropertyValueFactory<MenuFx, Integer>("price"));
        sizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() {
            @Override
            public Integer fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (NumberFormatException e) {
                    Alerts.dataTypeError();
                    return menuModel.getMenuFxObjectPropertyEdit().getPrice();
                }
            }
        }));
        sizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        menuTableView.getSelectionModel().selectedItemProperty().addListener((((observable, oldValue, newValue) -> {
            menuModel.setMenuFxObjectPropertyEdit(newValue);
        })));

        sizeModel = new SizeModel();
        sizeModel.initSizeList();
        sizeComboBox.setItems(sizeModel.getSizeFxObservableList());
        newSizeComboBox.getItems().addAll("ml", "cm");
        editSizeComboBox.setItems(sizeModel.getSizeFxObservableList());

        limitedTextField();
        activeButtons();
    }

    /**
     * Sets limits for TextField
     */
    public void limitedTextField() {
        nameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (nameTextField.getText().length() > 30) {
                    String s = nameTextField.getText().substring(0, 30);
                    nameTextField.setText(s);
                }
//                if (!newValue.matches("\\sa-zA-Z")) {
//                    nameTextField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
//                }
            }
        });

        this.priceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (priceTextField.getText().length() > 5) {
                    String s = priceTextField.getText().substring(0, 5);
                    priceTextField.setText(s);
                }
            }
        });

        this.newSizeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    newSizeTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (newSizeTextField.getText().length() > 5) {
                    String s = newSizeTextField.getText().substring(0, 5);
                    newSizeTextField.setText(s);
                }
            }
        });
    }

    /**
     * Determines when buttons are disabled
     */
    public void activeButtons() {
        addButton.disableProperty().bind(
                nameTextField.textProperty().isEmpty().or(
                        priceTextField.textProperty().isEmpty().or(
                                sizeComboBox.valueProperty().isNull()
                        )
                )
        );

        newSizeAddButton.disableProperty().bind(
                newSizeTextField.textProperty().isEmpty().or(
                        newSizeComboBox.valueProperty().isNull()
                )
        );

        editButton.disableProperty().bind(
                editSizeComboBox.valueProperty().isNull()
        );

        editVBox.setVisible(false);
    }

    /**
     * Backs to Main scene of application
     */
    public void onActionFirstMenuItem(ActionEvent actionEvent) {
        Stage dialogStage = (Stage) menuBar.getScene().getWindow();
        dialogStage.close();

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(MAIN_SCENE_FXML));

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
    }

    /**
     * To shutdown application
     */
    public void onActionSecondMenuItem(ActionEvent actionEvent) {
        HibernateUtil.getSessionFactory().close();
        Platform.exit();
        System.exit(0);
    }

    /**
     * To set selected item of combobox as sizeFx
     */
    public void onActionSizeComboBox(ActionEvent actionEvent) {
        sizeFx = sizeComboBox.getSelectionModel().getSelectedItem();

        if (editVBox.isVisible()) {
            editVBox.setVisible(false);
        }
    }

    /**
     * Starts addProduct method of class MenuModel.
     */
    public void onActionAddButton(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        int price = Integer.parseInt(priceTextField.getText());

        try {
            menuModel.addProduct(name, price, sizeFx);
        } catch (ApplicationException e) {
            Alerts.addRecordError(e.getMessage());
        }

        nameTextField.clear();
        priceTextField.clear();
        sizeComboBox.getSelectionModel().clearSelection();

        if (editVBox.isVisible()) {
            editVBox.setVisible(false);
        }
    }

    /**
     * To set selected item of combobox as newSize.
     */
    public void onActionNewSizeComboBox(ActionEvent actionEvent) {
        newSize = (String) newSizeComboBox.getSelectionModel().getSelectedItem();

        if (editVBox.isVisible()) {
            editVBox.setVisible(false);
        }
    }

    /**
     * Starts addSize method of class MenuModel
     */
    public void onActionNewSizeAddButton(ActionEvent actionEvent) {
        newSize = newSizeTextField.getText() + " " + newSize;

        try {
            sizeModel.addSize(newSize);
        } catch (ApplicationException e) {
            Alerts.addRecordError(e.getMessage());
        }

        newSizeTextField.clear();
        newSizeComboBox.getSelectionModel().clearSelection();

        if (editVBox.isVisible()) {
            editVBox.setVisible(false);
        }
    }

    /**
     * Starts updateProduct method of class MenuModel
     */
    public void onEditCommitNameColumn(TableColumn.CellEditEvent<MenuFx, String> menuFxStringCellEditEvent) {
        menuModel.getMenuFxObjectPropertyEdit().setName(menuFxStringCellEditEvent.getNewValue());

        try {
            menuModel.updateProduct();
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }

        if (editVBox.isVisible()) {
            editVBox.setVisible(false);
        }
    }

    /**
     * Starts updateProduct method of class MenuModel
     */
    public void onEditCommitPriceColumn(TableColumn.CellEditEvent<MenuFx, Integer> menuFxIntegerCellEditEvent) {
        menuModel.getMenuFxObjectPropertyEdit().setPrice(menuFxIntegerCellEditEvent.getNewValue());

        try {
            menuModel.updateProduct();
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }

        if (editVBox.isVisible()) {
            editVBox.setVisible(false);
        }
    }

    /**
     * Sets editVBox as visable
     */
    public void onEditStartSizeColumn(TableColumn.CellEditEvent<MenuFx, String> menuFxStringCellEditEvent) {
        menuModel.getMenuFxObjectPropertyEdit().setSize(menuFxStringCellEditEvent.getOldValue());
        editVBox.setVisible(true);
    }

    /**
     * To set selected item of combobox as sizeFx
     */
    public void onActionEditSizeComboBox(ActionEvent actionEvent) {
        sizeFx = editSizeComboBox.getSelectionModel().getSelectedItem();
    }

    /**
     * Sets editVBox as not visable
     */
    public void onActionEditCancelButton(ActionEvent actionEvent) {
        editVBox.setVisible(false);
        editSizeComboBox.getSelectionModel().clearSelection();
        menuModel.initMenuList();
    }

    /**
     * Starts updateSizeProduct method of class MenuModel
     */
    public void onActionEditButton(ActionEvent actionEvent) {
        try {
            menuModel.updateSizeProduct(sizeFx);
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }

        editSizeComboBox.getSelectionModel().clearSelection();
        editVBox.setVisible(false);
    }

    /**
     * Sets editVBox as not visable
     */
    public void onEditCommitSizeColumn(TableColumn.CellEditEvent<MenuFx, String> menuFxStringCellEditEvent) {
        editVBox.setVisible(false);
        menuModel.initMenuList();
    }

    /**
     * Starts deleteProduct method of class MenuModel
     */
    public void onActionMenuItem(ActionEvent actionEvent) {
        try {
            menuModel.deleteProduct();
        } catch (ApplicationException e) {
            Alerts.deleteRecordError(e.getMessage());
        }
    }
}
