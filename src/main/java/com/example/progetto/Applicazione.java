package com.example.progetto;

import com.example.progetto.controller_graf.HomeController;
import com.example.progetto.controller_graf.LoginController;
import com.example.progetto.controller_graf.UserHomeController;
import com.example.progetto.controller_graf.ViewTripController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.progetto.bean.UserBean;
public class Applicazione extends Application {

    private Stage stage;
    private Scene homeScene;
    private Scene ViewTripScene;
    private ViewTripController viewtrip;
    private UserHomeController userhome;
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


        FXMLLoader ViewTripLoader = new FXMLLoader(Applicazione.class.getResource("view_trip.fxml"));
        Parent ViewTripRoot = ViewTripLoader.load();
        ViewTripScene = new Scene(ViewTripRoot);

        stage.setScene(homeScene);
        stage.setTitle("Home!");

        //mi prendo il controller della schermata Home
        HomeController homeController = homeLoader.getController();
        homeController.setMain(this);


        viewtrip = ViewTripLoader.getController();

        viewtrip.setMain(this);


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