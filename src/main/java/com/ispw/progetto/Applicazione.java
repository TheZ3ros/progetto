package com.ispw.progetto;

import com.ispw.progetto.controller_graf.HomeController;
import com.ispw.progetto.utils.AppContext;
import com.ispw.progetto.utils.PersistenceMode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import viewseconda.ControllerHomeCLI;
import viewseconda.Printer;

import java.util.Objects;
import java.util.Scanner;

public class Applicazione extends Application {

    private Stage stage;
    private Scene homeScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scanner reader = new Scanner(System.in);

        Printer.printMessage("Come si vuole avviare il programma?");
        Printer.printMessage("1 - Interfaccia grafica");
        Printer.printMessage("2 - Linea di comando");

        boolean isGUI = false;
        boolean isValid = false;

        while (!isValid) {
            int n = reader.nextInt();
            if (n == 1) {
                isGUI = true;
                isValid = true;
            } else if (n == 2) {
                isValid = true;
            } else {
                Printer.printMessage("Inserire un'opzione valida");
            }
        }

        Printer.printMessage("Scegliere modalità:");
        Printer.printMessage("1 - Versione completa (salvataggio permanente)");
        Printer.printMessage("2 - Versione demo (salvataggio in memoria)");

        PersistenceMode mode = PersistenceMode.DB;
        isValid = false;

        while (!isValid) {
            int m = reader.nextInt();
            if (m == 1) {
                mode = isGUI ? PersistenceMode.DB : PersistenceMode.CSV;
                isValid = true;
            } else if (m == 2) {
                mode = PersistenceMode.MEMORY;
                isValid = true;
            } else {
                Printer.printMessage("Inserire un'opzione valida");
            }
        }

        AppContext context = new AppContext(isGUI, mode);

        if (isGUI) {
            this.stage = stage;

            FXMLLoader homeLoader = new FXMLLoader(Applicazione.class.getResource("view1/home.fxml"));
            Parent homeRoot = homeLoader.load();
            homeScene = new Scene(homeRoot);

            stage.setResizable(false);
            stage.setScene(homeScene);
            stage.setTitle("Home!");

            HomeController homeController = homeLoader.getController();
            homeController.setStage(stage);
            homeController.setAppContext(context);  // <-- Nuova riga: passaggio del contesto

            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/immagini/infinito.png"))));

            stage.show();
        } else {
            ControllerHomeCLI controllerHomeCLI = new ControllerHomeCLI(context);
            controllerHomeCLI.start();
        }
    }

    public void vaiAHome() {
        stage.setScene(homeScene);
        stage.setTitle("Home");
    }

    public Stage getStage() {
        return stage;
    }
}
