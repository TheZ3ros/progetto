package view2.user;

import com.example.progetto.bean.BookBean;
import com.example.progetto.bean.BuonoBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.controller_app.PagamentoControllerApp;
import com.example.progetto.exception.*;
import com.example.progetto.pattern.factory.EntityFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PaymentCLI {
    private final TripBean trip;
    private HomeLoginCLI login;
    private final UserBean user;
    private Timer timer;
    PaymentCLI(TripBean trip, UserBean user){
        this.trip =trip;
        this.user=user;
    }
    public void start(HomeLoginCLI login) throws CardNotTrueException, IOException {
        this.login=login;
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Si vuole inserire un coupon?");
        Printer.printMessage("1- sì");
        Printer.printMessage("2- no");
        startTimer();
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
            if (nome.isEmpty()){
                throw new IOException();
            }
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
            } catch (ExistsUserException e) {
                throw new RuntimeException(e);
            }
    }
    private void startTimer() {
        timer = new Timer();
        Printer.printMessage("6 minuti per concludere il pagamento");
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Il tempo è scaduto!");
                try {
                    login.start();
                } catch (SQLException | IOException | PlacesTerminatedException | AlreadyPrenotedException |
                         ExistsUserException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        long delay = 600000;
        timer.schedule(task, delay);
    }
}



