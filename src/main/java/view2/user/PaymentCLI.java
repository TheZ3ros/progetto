package view2.user;

import com.example.progetto.bean.BookBean;
import com.example.progetto.bean.BuonoBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.controller_app.PagamentoControllerApp;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.CardNotTrueException;
import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.exception.PlacesTerminatedException;
import com.example.progetto.pattern.factory.BeanFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class PaymentCLI {
    private final TripBean trip;
    private final BeanFactory user;
    PaymentCLI(TripBean trip, BeanFactory user){
        this.trip =trip;
        this.user=user;
    }
    public void start(HomeLoginCLI login) throws CardNotTrueException {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Si vuole inserire un coupon?");
        Printer.printMessage("1- sì");
        Printer.printMessage("2- no");
        Scanner reader = new Scanner(System.in);
        int n;
        n = reader.nextInt();
            if (n==1) {
                Printer.printMessage("inserire coupon");
                String coupon = scanner.nextLine();
                BuonoBean buonoBean = new BuonoBean();
                buonoBean.setCodice(coupon);
                PagamentoControllerApp pagamentoControllerApp = new PagamentoControllerApp();
                try {
                    buonoBean = pagamentoControllerApp.checkBuono(buonoBean);
                } catch (SQLException | IOException | NotValidCouponException e) {
                    Printer.printMessage(e.getMessage());
                }
                int prezzo = (int) trip.getPrice() - buonoBean.getValore();
                Printer.printMessage("Nuovo prezzo:" + prezzo);
            }
        Printer.printMessage("inserire nome");
            String nome = scanner.nextLine();
        Printer.printMessage("inserire numero carta");
            String numeroCarta = scanner.nextLine();
        Printer.printMessage("inserire cvv");
            String cvvCode = scanner.nextLine();
        Printer.printMessage("inserire data");
        Printer.printMessage("Inserisci una data (formato: YYYY-MM-DD):");
        String input = scanner.nextLine();

        // Converte la stringa in un oggetto LocalDate
        LocalDate date = LocalDate.parse(input);
            PagamentoControllerApp pagamentoControllerApp = new PagamentoControllerApp();
            try {
                pagamentoControllerApp.checkCard(numeroCarta, cvvCode, date);
            } catch (CardNotTrueException e) {
                Printer.printMessage(e.getMessage());
            }
            BookTripController bookTripController = new BookTripController();
            BookBean bookBean = new BookBean(user.getUsername(), trip.getId());
            try {
                bookTripController.bookTrip(bookBean);
                Printer.printMessage("Prenotazione avvenuta con successo");
                login.start();
            } catch (SQLException | IOException | PlacesTerminatedException | AlreadyPrenotedException e) {
                Printer.printMessage(e.getMessage());
            }
        }
    }


