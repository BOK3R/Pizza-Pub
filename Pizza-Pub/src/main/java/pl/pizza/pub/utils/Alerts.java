package pl.pizza.pub.utils;

import javafx.scene.control.Alert;

import java.util.ResourceBundle;

/**
 * Contains Alerts for exceptions and errors
 */
public class Alerts {
    static ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

    /**
     * Alert for error with database conection
     * @param massage message to set as content text of alert
     */
    public static void databaseConnectionAlert(String massage) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.databaseConnectionAlert.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.databaseConnectionAlert.HeaderText"));
        errorAlert.setContentText(massage);
        errorAlert.showAndWait();
    }

    /**
     * Alert for incorrect login credentials
     */
    public static void incorrectLoginAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.incorrectLoginAlert.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.incorrectLoginAlert.HeaderText"));
        errorAlert.setContentText(bundle.getString("Alerts.incorrectLoginAlert.ContentText"));
        errorAlert.showAndWait();
    }

    /**
     * Alert for logging without sufficient permission
     */
    public static void noPermissionAlert() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("Alerts.noPermissionAlert.Title"));
        informationAlert.setHeaderText(bundle.getString("Alerts.noPermissionAlert.HeaderText"));
        informationAlert.setContentText(bundle.getString("Alerts.noPermissionAlert.ContentText"));
        informationAlert.showAndWait();
    }

    /**
     * Alert for unexpected login error
     */
    public static void unknowLoginError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.unknowLoginError.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.unknowLoginError.HeaderText"));
        errorAlert.setContentText(bundle.getString("Alerts.unknowLoginError.ContentText"));
        errorAlert.showAndWait();
    }

    /**
     * Alert for error while adding record to database
     * @param message message to set as content text of alert
     */
    public static void addRecordError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.addRecordError.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.addRecordError.HeaderText"));
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    /**
     * Alert for error while updating record to database
     * @param message message to set as content text of alert
     */
    public static void updateRecordError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.updateRecordError.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.updateRecordError.HeaderText"));
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    /**
     * Alert for error while deleting record to database
     * @param message message to set as content text of alert
     */
    public static void deleteRecordError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.deleteRecordError.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.deleteRecordError.HeaderText"));
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    /**
     * Alert for incorrect type of data
     */
    public static void dataTypeError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.dataTypeError.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.dataTypeError.HeaderText"));
        errorAlert.setContentText(bundle.getString("Alerts.dataTypeError.ContentText"));
        errorAlert.showAndWait();
    }

    /**
     * Alert for error with gets order
     * @param message message to set as content text of alert
     */
    public static void getOrderError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("Alerts.getOrderError.Title"));
        errorAlert.setHeaderText(bundle.getString("Alerts.getOrderError.HeaderText"));
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }
}
