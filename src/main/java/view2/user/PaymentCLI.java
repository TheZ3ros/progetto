package view2.user;

import com.example.progetto.bean.BookBean;
import com.example.progetto.bean.BuonoBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.controller_app.PagamentoControllerApp;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.CardNotTrueException;
import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.exception.PlacesTerminatedException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class PaymentCLI {
    private final TripBean trip;
    private final UserBean user;
    PaymentCLI(TripBean trip, UserBean user){
        this.trip =trip;
        this.user=user;
    }
    public void start(HomeLoginCLI login) throws CardNotTrueException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Si vuole inserire un coupon?");
        System.out.println("1- sì");
        System.out.println("2- no");
        Scanner reader = new Scanner(System.in);
        int n;
        n = reader.nextInt();
            if (n==1) {
                System.out.println("inserire coupon");
                String coupon = scanner.nextLine();
                BuonoBean buonoBean = new BuonoBean();
                buonoBean.setCodice(coupon);
                PagamentoControllerApp pagamentoControllerApp = new PagamentoControllerApp();
                try {
                    buonoBean = pagamentoControllerApp.checkBuono(buonoBean);
                } catch (SQLException | IOException | NotValidCouponException e) {
                    System.out.println(e.getMessage());
                }
                int prezzo = (int) trip.getPrice() - buonoBean.getValore();
                System.out.println("Nuovo prezzo:" + prezzo);
            }
                System.out.println("inserire nome");
            String nome = scanner.nextLine();
            System.out.println("inserire numero carta");
            String numeroCarta = scanner.nextLine();
            System.out.println("inserire cvv");
            String cvvCode = scanner.nextLine();
            System.out.println("inserire data");
        System.out.println("Inserisci una data (formato: YYYY-MM-DD):");
        String input = scanner.nextLine();

        // Converte la stringa in un oggetto LocalDate
        LocalDate date = LocalDate.parse(input);
            PagamentoControllerApp pagamentoControllerApp = new PagamentoControllerApp();
            try {
                pagamentoControllerApp.checkCard(numeroCarta, cvvCode, date);
            } catch (CardNotTrueException e) {
                System.out.println(e.getMessage());
            }
            BookTripController bookTripController = new BookTripController();
            BookBean bookBean = new BookBean(user.getUsername(), trip.getId());
            try {
                bookTripController.bookTrip(bookBean);
                System.out.println("Prenotazione avvenuta con successo");
                login.start();
            } catch (SQLException | IOException | PlacesTerminatedException | AlreadyPrenotedException e) {
                System.out.println(e.getMessage());
            }
        }
    }


