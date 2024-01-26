package com.example.progetto;

import com.example.progetto.controller_graf.HomeController;
import com.example.progetto.controller_graf.LoginController;
import com.example.progetto.controller_graf.UserHomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.progetto.entity.User;
public class Applicazione extends Application {

    private Stage stage;
    private Scene homeScene;
    private Scene loginScene;
    private Scene homeloginScene;
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

        //carico login
        FXMLLoader loginLoader = new FXMLLoader(Applicazione.class.getResource("login.fxml"));
        Parent loginRoot = loginLoader.load();
        loginScene = new Scene(loginRoot);


        FXMLLoader UserHomeLoader = new FXMLLoader(Applicazione.class.getResource("home_login.fxml"));
        Parent UserHomeRoot = UserHomeLoader.load();
        homeloginScene = new Scene(UserHomeRoot);



        stage.setScene(homeScene);
        stage.setTitle("Home!");

        //mi prendo il controller della schermata Home
        HomeController homeController = homeLoader.getController();
        homeController.setMain(this);

        // Ottenere il controller della schermata di login
        LoginController loginController = loginLoader.getController();

        userhome = UserHomeLoader.getController();

        // Impostare il riferimento a Main nel controller di login
        loginController.setMain(this);

        userhome.setMain(this);


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

    public void vai_a_UserHome(User utente){
        userhome.setUser(utente);
        stage.setScene(homeloginScene);
        userhome.setButtonText();
        stage.setTitle("Accedi");

    }


}