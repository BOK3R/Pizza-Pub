package pl.pizza.pub.controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.pizza.pub.models.EmployeesFx;
import pl.pizza.pub.models.EmployeesModel;
import pl.pizza.pub.models.RoleFx;
import pl.pizza.pub.models.RoleModel;
import pl.pizza.pub.utils.Alerts;
import pl.pizza.pub.utils.ApplicationException;
import pl.pizza.pub.utils.HibernateUtil;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller class for Admin.fxml;
 */
public class AdminController {

    public static final String MAIN_SCENE_FXML = "/fxml/MainScene.fxml";
    private EmployeesModel employeesModel;
    private RoleModel roleModel;
    private RoleFx roleFx;

    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<EmployeesFx> employeesTableView;
    @FXML
    private TableColumn<EmployeesFx, String> nameColumn;
    @FXML
    private TableColumn<EmployeesFx, String> surnameColumn;
    @FXML
    private TableColumn<EmployeesFx, String> loginColumn;
    @FXML
    private TableColumn<EmployeesFx, String> passwordColumn;
    @FXML
    private TableColumn<EmployeesFx, String> roleColumn;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ComboBox<RoleFx> addRoleComboBox;
    @FXML
    private Button addButton;
    @FXML
    private VBox editVBox;
    @FXML
    private ComboBox<RoleFx> editComboBox;
    @FXML
    private Button editButton;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        employeesModel = new EmployeesModel();
        employeesModel.initEmployeesList();

        employeesTableView.setItems(employeesModel.getEmployeesFxObservableList());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        loginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        employeesTableView.getSelectionModel().selectedItemProperty().addListener((((observable, oldValue, newValue) -> {
            employeesModel.setEmployeesFxObjectPropertyEdit(newValue);
        })));

        roleModel = new RoleModel();
        roleModel.initRoleList();
        addRoleComboBox.setItems(roleModel.getRoleFxObservableList());
        editComboBox.setItems(roleModel.getRoleFxObservableList());

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

        surnameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (surnameTextField.getText().length() > 30) {
                    String s = surnameTextField.getText().substring(0, 30);
                    surnameTextField.setText(s);
                }
//                if (!newValue.matches("\\sa-zA-Z")) {
//                    surnameTextField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
//                }
            }
        });

        loginTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (loginTextField.getText().length() > 30) {
                    String s = loginTextField.getText().substring(0, 30);
                    loginTextField.setText(s);
                }
            }
        });

        passwordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (passwordTextField.getText().length() > 30) {
                    String s = passwordTextField.getText().substring(0, 30);
                    passwordTextField.setText(s);
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
                        surnameTextField.textProperty().isEmpty().or(
                                loginTextField.textProperty().isEmpty().or(
                                        passwordTextField.textProperty().isEmpty().or(
                                                addRoleComboBox.valueProperty().isNull()
                                        )
                                )
                        )
                )
        );

        editVBox.setVisible(false);

        editButton.disableProperty().bind(
                editComboBox.valueProperty().isNull()
        );
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
     * Starts addEmployee method of class EmployeeModel
     */
    public void onActionAddButton(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        try {
            employeesModel.addEmployee(name, surname, login, password, roleFx);
        } catch (ApplicationException e) {
            Alerts.addRecordError(e.getMessage());
        }

        nameTextField.clear();
        surnameTextField.clear();
        loginTextField.clear();
        passwordTextField.clear();
        addRoleComboBox.getSelectionModel().clearSelection();
    }

    /**
     * To set selected item as roleFx
     * @param actionEvent
     */
    public void onActionAddRoleComboBox(ActionEvent actionEvent) {
        roleFx = addRoleComboBox.getSelectionModel().getSelectedItem();
    }

    /**
     * Starts updateEmployee method of class EmployeeModel
     */
    public void onEditCommitNameColumn(TableColumn.CellEditEvent<EmployeesFx, String> employeesFxStringCellEditEvent) {
        employeesModel.getEmployeesFxObjectPropertyEdit().setName(employeesFxStringCellEditEvent.getNewValue());
        try {
            employeesModel.updateEmployee();
        } catch (ApplicationException e) {
            employeesModel.initEmployeesList();
            Alerts.updateRecordError(e.getMessage());
        }
    }

    /**
     * Starts updateEmployee method of class EmployeeModel
     */
    public void onEditCommitSurnameColumn(TableColumn.CellEditEvent<EmployeesFx, String> employeesFxStringCellEditEvent) {
        employeesModel.getEmployeesFxObjectPropertyEdit().setSurname(employeesFxStringCellEditEvent.getNewValue());
        try {
            employeesModel.updateEmployee();
        } catch (ApplicationException e) {
            employeesModel.initEmployeesList();
            Alerts.updateRecordError(e.getMessage());
        }
    }

    /**
     * Starts updateEmployee method of class EmployeeModel
     */
    public void onEditCommitLoginColumn(TableColumn.CellEditEvent<EmployeesFx, String> employeesFxStringCellEditEvent) {
        employeesModel.getEmployeesFxObjectPropertyEdit().setLogin(employeesFxStringCellEditEvent.getNewValue());
        try {
            employeesModel.updateEmployee();
        } catch (ApplicationException e) {
            employeesModel.initEmployeesList();
            Alerts.updateRecordError(e.getMessage());
        }
    }

    /**
     * Starts updateEmployee method of class EmployeeModel
     */
    public void onEditCommitPasswordColumn(TableColumn.CellEditEvent<EmployeesFx, String> employeesFxStringCellEditEvent) {
        employeesModel.getEmployeesFxObjectPropertyEdit().setPassword(employeesFxStringCellEditEvent.getNewValue());
        try {
            employeesModel.updateEmployee();
        } catch (ApplicationException e) {
            employeesModel.initEmployeesList();
            Alerts.updateRecordError(e.getMessage());
        }
    }

    /**
     * Sets editVBox as visable
     */
    public void onEditStartRoleColumn(TableColumn.CellEditEvent<EmployeesFx, String> employeesFxStringCellEditEvent) {
        employeesModel.getEmployeesFxObjectPropertyEdit().setRole(employeesFxStringCellEditEvent.getOldValue());
        editVBox.setVisible(true);
    }

    /**
     * Starts updateEmployee method of class EmployeeModel
     */
    public void onEditCommitRoleColumn(TableColumn.CellEditEvent<EmployeesFx, String> employeesFxStringCellEditEvent) {
        employeesModel.initEmployeesList();
        editVBox.setVisible(false);
        editComboBox.getSelectionModel().clearSelection();
    }

    /**
     * To set selected item as roleFx
     */
    public void onActionEditComboBox(ActionEvent actionEvent) {
        roleFx = editComboBox.getSelectionModel().getSelectedItem();
    }

    /**
     * Sets editVBox as not visable
     */
    public void onActionEditCancelButton(ActionEvent actionEvent) {
        editVBox.setVisible(false);
        editComboBox.getSelectionModel().clearSelection();
        employeesModel.initEmployeesList();
    }

    /**
     * Starts updateRoleEmployee method of class EmployeeModel
     */
    public void onActionEditButton(ActionEvent actionEvent) {
        try {
            employeesModel.updateRoleEmployee(roleFx);
        } catch (ApplicationException e) {
            Alerts.updateRecordError(e.getMessage());
        }
        editComboBox.getSelectionModel().clearSelection();
        editVBox.setVisible(false);
    }

    /**
     * Starts deleteEmployee methdd of class EmployeeModel
     */
    public void onActionMenuItem(ActionEvent actionEvent) {
        try {
            employeesModel.deleteEmployee();
        } catch (ApplicationException e) {
            Alerts.deleteRecordError(e.getMessage());
        }
    }
}
