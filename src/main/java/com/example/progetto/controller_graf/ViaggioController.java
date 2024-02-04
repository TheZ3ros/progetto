package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ViaggioController {
    @FXML
    private ImageView imagine;
    @FXML
    private Button title;
    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    private Applicazione main;
    private TripBean bean;
    private UserBean user;
    @FXML
    public void setMain(Applicazione main){

        this.main = main;
    }

    public void setUser(UserBean user){
        this.user=user;
    }

    public void createbox(TripBean bean) {
        this.bean=bean;
        Image image= BookTripController.bytesToImage(bean.getImage());
        imagine.setImage(image);
            title.setText(bean.getCity());
            data.setText(bean.getData_and() +"/" + bean.getData_rit());
            prezzo.setText((int)bean.getPrice()+"€");
        }

    public void pagetrip() throws IOException, SQLException {
            FXMLLoader paginaLoader = new FXMLLoader(Applicazione.class.getResource("pagetrip.fxml"));
            Parent PageRoot = paginaLoader.load();
            Scene paginaScene = new Scene(PageRoot);
            PageTripController pagetrip = paginaLoader.getController();
            pagetrip.setMain(main);
            pagetrip.set_trip(bean);
            pagetrip.setCurrentUser(user);
            Stage stage = main.getStage();
            stage.setScene(paginaScene);
            pagetrip.charge();
            pagetrip.setButtonText();
        }
    }



