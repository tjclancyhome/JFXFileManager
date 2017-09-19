package org.tjc.jfx.filemanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.tjc.common.utils.LogObjectQuery;

@Slf4j
public class FileManagerApp extends Application {

    @Override
    @SuppressWarnings("Convert2Lambda")
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = fxmlLoader.load();

        FileManagerSceneController controller = fxmlLoader.getController();

        LogObjectQuery.Slf4jQuery.getLogLevelsState(log).forEach(System.out::println);

        log.debug("controller: {}", controller);
        log.debug("setting stage in controller: stage: {}", stage);

        controller.setStage(stage);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
