package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.Exception.CardNotTrueException;
import com.example.progetto.Exception.NotValidCouponException;
import com.example.progetto.bean.BuonoBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.controller_app.PagamentoControllerApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

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
    private UserBean currentUser;
    private static final String ACTION ="Informazione";
    public void setMain(Applicazione main) {

        this.main = main;
    }
    @FXML
    private void vaiAHome() {

        main.vaiAHome();
    }
    
    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page=new ViewTripController();
        page.viewTrip(main, currentUser);

    }
    public void setUser(UserBean utente){

        currentUser=utente;
    }
    public void setTrip(TripBean trip){
        this.currentTrip=trip;
    }
    public void setButtonText() {

        agency.setText(currentUser.getUsername());
        price.setText("totale"+ (int) currentTrip.getPrice());

    }

    @FXML
    private void submit() throws CardNotTrueException, SQLException, IOException, NotValidCouponException {

        String numeroCarta=numero.getText();
        String cvvCode=cvv.getText();
        try {
            if (numeroCarta.length() != 16 || cvvCode.length() != 3) {
                throw new CardNotTrueException("dati carta non validi");
            }
            String buonoSpesa = (buono.getText());
            BuonoBean buonoBean = new BuonoBean();
            buonoBean.setCodice(buonoSpesa);
            CheckBuono(buonoBean);
        }
        catch(CardNotTrueException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }


    private void CheckBuono(BuonoBean buonoBean) throws SQLException, IOException {
        PagamentoControllerApp pagamento=new PagamentoControllerApp();
        try{
            buonoBean=pagamento.CheckBuono(buonoBean);
            int totale=((int)currentTrip.getPrice()-buonoBean.getValore());
            price.setText("totale"+totale);

        }
        catch(NotValidCouponException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(ACTION);
            alert.setHeaderText(null);
            alert.setContentText("Coupon non valido");
            alert.showAndWait();
        }

    }
}
