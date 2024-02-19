package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.exception.*;
import com.example.progetto.bean.BookBean;
import com.example.progetto.bean.BuonoBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.controller_app.PagamentoControllerApp;
import com.example.progetto.pattern.factory.BeanFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class PagamentoController {

    @FXML
    private Button agency;
    @FXML
    private TextField numero;
    @FXML
    private TextField cvv;
    @FXML
    private Text price;
    @FXML
    private DatePicker scadenza;
    @FXML
    private TextField buono;
    private Applicazione main;
    private TripBean currentTrip;
    private BeanFactory currentUser;
    private static final String ACTION ="Informazione";
    public void setMain(Applicazione main) {

        this.main = main;
    }
    @FXML
    public void vaiAHome() {

        main.vaiAHome();
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page=new ViewTripController();
        page.viewTrip(main, currentUser);

    }
    public void setUser(BeanFactory utente){

        currentUser=utente;
    }
    public void setTrip(TripBean trip){
        this.currentTrip=trip;
    }
    public void setButtonText() {

        agency.setText(currentUser.getUsername());
        price.setText("Totale "+ (int) currentTrip.getPrice()+"€");

    }

    @FXML
    private void submit() throws SQLException, IOException {
        String numeroCarta=numero.getText();
        String cvvCode=cvv.getText();
        LocalDate data=scadenza.getValue();

        try {

            PagamentoControllerApp pagamentoControllerApp=new PagamentoControllerApp();
            pagamentoControllerApp.checkCard(numeroCarta,cvvCode,data);
            BookBean book=new BookBean(currentUser.getUsername(),currentTrip.getId());
            BookTripController bookTripController=new BookTripController();
            bookTripController.bookTrip(book);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prenotato");
            alert.setHeaderText(null);
            alert.setContentText("prenotazione effettuata correttamente");
            alert.showAndWait();
        }
        catch(CardNotTrueException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (PlacesTerminatedException e) {
            Alert alert2=new Alert(Alert.AlertType.ERROR);
            alert2.setTitle(ACTION);
            alert2.setHeaderText(null);
            alert2.setContentText(e.getMessage());
            alert2.showAndWait();
        } catch (AlreadyPrenotedException e) {
            Alert alert3=new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(ACTION);
            alert3.setHeaderText(null);
            alert3.setContentText(e.getMessage());
            alert3.showAndWait();
        }


    }

    @FXML
    private void checkBuono() throws SQLException, IOException {
        String buonoSpesa = buono.getText();
        BuonoBean buonoBean=new BuonoBean();
        buonoBean.setCodice(buonoSpesa);
        PagamentoControllerApp pagamento=new PagamentoControllerApp();
        try{
            buonoBean=pagamento.checkBuono(buonoBean);
            int totale=((int)currentTrip.getPrice()-buonoBean.getValore());
            price.setText("Totale "+totale+"€");

        }
        catch(NotValidCouponException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(ACTION);
            alert.setHeaderText(null);
            alert.setContentText("Coupon non valido");
            alert.showAndWait();
            buono.setText(null);
        }

    }
    @FXML
    public void info() throws IOException, SQLException, ExistsUserException {
        FXMLLoader infoLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/info_user.fxml"));
        Parent inforoot = infoLoader.load();
        Scene myTripScene = new Scene(inforoot);
        InfoUserController infoController = infoLoader.getController();
        infoController.setMain(main);
        infoController.setUser(currentUser);
        infoController.setInfo();
        Stage stage = main.getStage();
        stage.setTitle("I miei viaggi");
        stage.setScene(myTripScene);

    }
}
