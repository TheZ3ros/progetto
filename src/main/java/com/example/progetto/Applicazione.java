package com.example.progetto;

import com.example.progetto.controller_graf.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view2.ControllerHomeCLI;

import java.util.Scanner;

public class Applicazione extends Application {

    private Stage stage;
    private Scene homeScene;
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Come si vuole avviare il programma?");
        int n;
        System.out.println("1-Interfaccia grafica");
        System.out.println("2-Linea di comando");
        Scanner reader = new Scanner(System.in);
        while (true) {
            n = reader.nextInt();
            if(n==1){
                this.stage = stage;

                //carico la home
                FXMLLoader homeLoader = new FXMLLoader(Applicazione.class.getResource("view1/home.fxml"));
                Parent homeRoot = homeLoader.load();
                homeScene = new Scene(homeRoot);

                stage.setResizable(false);
                stage.setScene(homeScene);
                stage.setTitle("Home!");

                //mi prendo il controller della schermata Home
                HomeController homeController = homeLoader.getController();
                homeController.setMain(this);

                stage.show();
                break;
            }
            else if(n==2){
                ControllerHomeCLI controllerHomeCLI=new ControllerHomeCLI();
                controllerHomeCLI.start();
                break;
            }
            else{
                System.out.println("inserire un'opzione valida");
            }

        }

    }

    public void vaiAHome(){
        stage.setScene(homeScene);
        stage.setTitle("Home");
    }

    public Stage getStage(){

        return stage;
    }
}