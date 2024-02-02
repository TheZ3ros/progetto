package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.TripCreationBean;
import com.example.progetto.controller_app.CreateTripController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

public class ViewTripCreationController {


    private Applicazione main;
    private String image_path;
    private AgencyBean currentUser;
    @FXML
    private Button agency;
    @FXML
    private ImageView image_viewer;
    @FXML
    private TextField nome_città;
    @FXML
    private TextField disponibili;
    @FXML
    private DatePicker data_and;
    @FXML
    private DatePicker data_rit;
    @FXML
    private TextField prezzo;


    public void setMain(Applicazione main) {

        this.main = main;
    }

    public void setUser(AgencyBean utente){

        currentUser=utente;
    }
    public void setButtonText() {

        agency.setText(currentUser.getUsername());
    }

    @FXML
    private void vai_a_Home(){

        main.vai_a_Home();
    }

    @FXML
    private void insert_image(){
        FileChooser image_uploader = new FileChooser();
        image_uploader.setTitle("Seleziona l'immagine");
        image_uploader.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("File immagine","*.png","*.jpg","*.jpeg","*.gif"));
        File selectedFile = image_uploader.showOpenDialog(new Stage());
        if(selectedFile!=null){
            image_path = selectedFile.getAbsolutePath();
            Image image = new Image(selectedFile.toURI().toString());
            image_viewer.setImage(image);
        }

    }

    @FXML
    private void submit(){
        try{
            String city = nome_città.getText().trim();
            if (city.isEmpty() || image_path.isEmpty()){
                throw new IllegalArgumentException("Stringhe vuote");
            }
            int available = Integer.parseInt(disponibili.getText());

            LocalDate check_andata = data_and.getValue();
            LocalDate check_ritorno = data_rit.getValue();

            if (check_andata.isBefore(LocalDate.now())){
                data_and.setValue(null);
                throw new RuntimeException();
            }
            else if (check_ritorno.isBefore(check_andata)){
                data_rit.setValue(null);
                throw new RuntimeException();
            }
            Date andata = Date.valueOf(check_andata);
            Date ritorno = Date.valueOf(check_ritorno);
            float price = Float.parseFloat(prezzo.getText());
            TripCreationBean trip = new TripCreationBean(city,available,andata,ritorno,price,image_path);
            CreateTripController creation = new CreateTripController(trip);
            System.out.println("Check");
            creation.upload_trip();
        } catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creazione fallita");
            alert.setHeaderText(null);
            alert.setContentText("Ricontrollare i dati inseriti");
            alert.showAndWait();
        }





    }
}
