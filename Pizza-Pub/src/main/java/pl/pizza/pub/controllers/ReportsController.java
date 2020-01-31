package pl.pizza.pub.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.pizza.pub.utils.HibernateUtil;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller class of Reports.fxml
 */
public class ReportsController {

    public static final String MAIN_SCENE_FXML = "/fxml/MainScene.fxml";

    @FXML
    private BorderPane borderPane;
    @FXML
    private ReportsTopController reportsTopController;
    @FXML
    private MenuBar menuBar;

    /**
     * Initializes starting parameters of scene
     */
    public void initialize() {
        reportsTopController.setReportsController(this);
    }

    /**
     * Opens new scene
     * @param fxmlPath path of new scene
     */
    @FXML
    public void setCenter(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));

        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);

        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(parent);
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
}
