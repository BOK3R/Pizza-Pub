package pl.pizza.pub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import org.hibernate.Session;
import pl.pizza.pub.utils.HibernateUtil;

import java.util.ResourceBundle;

/**
 * Main class of application
 */
public class Main extends Application{

    public static final String MAIN_SCENE_FXML = "/fxml/MainScene.fxml";

    /**
     * Start method of application
     * @param primaryStage primary stage of application
     * @throws Exception Exception which method throws
     */
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(MAIN_SCENE_FXML));

        Session session = HibernateUtil.getSessionFactory().openSession();

        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);

        BorderPane mainBorderPane = loader.load();
        Scene scene = new Scene(mainBorderPane);

        primaryStage.setTitle(bundle.getString("Main.titleApplication"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method of application
     * @param args arguments of application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
