package com.example.progetto;

import com.example.progetto.controller_graf.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Applicazione extends Application {

    private Stage stage;
    private Scene homeScene;
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


        stage.setScene(homeScene);
        stage.setTitle("Home!");

        //mi prendo il controller della schermata Home
        HomeController homeController = homeLoader.getController();
        homeController.setMain(this);

        stage.show();
    }

    public void vai_a_Home(){
        stage.setScene(homeScene);
        stage.setTitle("Home");
    }

public Stage getStage(){

        return stage;
}
}