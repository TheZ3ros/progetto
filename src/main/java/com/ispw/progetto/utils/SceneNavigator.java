package com.ispw.progetto.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneNavigator {

    private SceneNavigator() {
        // Utility class, no instantiation allowed
    }

    public static void switchTo(Stage stage, String fxmlPath, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
        Parent root = loader.load();

        if (controller != null) {
            Object loadedController = loader.getController();
            if (loadedController instanceof StageAware stageAware) {
                stageAware.setStage(stage);
            }
        }

        stage.setScene(new Scene(root));
        String title = fxmlPath.substring(fxmlPath.lastIndexOf('/') + 1, fxmlPath.lastIndexOf('.'));
        stage.setTitle(capitalize(title));
    }

    private static String capitalize(String title) {
        if (title == null || title.isEmpty()) return title;
        return title.substring(0, 1).toUpperCase() + title.substring(1);
    }
}
