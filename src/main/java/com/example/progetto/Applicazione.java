package com.example.progetto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applicazione extends Application {

    private Stage stage;
    private Scene homeScene;
    private Scene loginScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        //carico la home
        FXMLLoader homeLoader = new FXMLLoader(Applicazione.class.getResource("home.fxml"));
        Parent homeRoot = homeLoader.load();
        homeScene = new Scene(homeRoot);

        //carico login
        FXMLLoader loginLoader = new FXMLLoader(Applicazione.class.getResource("login.fxml"));
        Parent loginRoot = loginLoader.load();
        loginScene = new Scene(loginRoot);

        stage.setScene(homeScene);
        stage.setTitle("Home!");

        //mi prendo il controller della schermata Home
        HomeController homeController = homeLoader.getController();
        homeController.setMain(this);

        // Ottenere il controller della schermata di login
        LoginController loginController = loginLoader.getController();

        // Impostare il riferimento a Main nel controller di login
        loginController.setMain(this);

        stage.show();
    }

    public void vai_a_Home(){
        stage.setScene(homeScene);
        stage.setTitle("Home");
    }

    public void vai_a_Login(){
        stage.setScene(loginScene);
        stage.setTitle("Accedi");

    }

}