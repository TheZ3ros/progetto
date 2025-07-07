package viewseconda.user;

import com.ispw.progetto.bean.BookBean;
import com.ispw.progetto.bean.BuonoBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.controller_app.PagamentoControllerApp;
import com.ispw.progetto.exception.*;
import com.ispw.progetto.utils.AppContext;
import com.ispw.progetto.utils.PersistenceMode;
import viewseconda.Printer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PaymentCLI {
    private final TripBean trip;
    private final UserBean user;

    public PaymentCLI(TripBean trip, UserBean user) {
        this.trip = trip;
        this.user = user;
    }

    public void start(UserHomeNavigator navigator) throws CardNotTrueException, IOException {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Si vuole inserire un coupon?");
        Printer.printMessage("1 - sì\n2 - no");
        startTimer(navigator);

        int n = scanner.nextInt();
        scanner.nextLine(); // Consuma newline

        if (n == 1) {
            Printer.printMessage("Inserire coupon:");
            String coupon = scanner.nextLine();
            BuonoBean buonoBean = new BuonoBean();
            buonoBean.setCodice(coupon);
            try {
                buonoBean = new PagamentoControllerApp().checkBuono(buonoBean);
                int prezzo = (int) trip.getPrice() - buonoBean.getValore();
                Printer.printMessage("Nuovo prezzo: " + prezzo);
            } catch (Exception e) {
                Printer.printMessage(e.getMessage());
            }
        }

        Printer.printMessage("Inserire nome:");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) throw new IOException();

        Printer.printMessage("Inserire numero carta:");
        String numeroCarta = scanner.nextLine();
        Printer.printMessage("Inserire cvv:");
        String cvvCode = scanner.nextLine();
        Printer.printMessage("Inserisci data (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        try {
            new PagamentoControllerApp().checkCard(numeroCarta, cvvCode, date);
            AppContext context = AppContext.getInstance();
            int tripId;
            if (context.getPersistenceMode() == PersistenceMode.MEMORY) {
                tripId = 1; // ID di default per la modalità demo
            } else {
                tripId = trip.getId();
            }
            BookBean bookBean = new BookBean(user.getUsername(), tripId);
            BookTripController bookTripController = new BookTripController(); // 🔹

            bookTripController.bookTrip(bookBean);
            Printer.printMessage("Prenotazione avvenuta con successo");
            navigator.goToHome();
        } catch (Exception e) {
            Printer.printMessage(e.getMessage());
            navigator.goToHome();
        }
    }

    private void startTimer(UserHomeNavigator navigator) {
        Timer timer = new Timer();
        Printer.printMessage("6 minuti per concludere il pagamento");
        timer.schedule(new TimerTask() {
            public void run() {
                Printer.printMessage("Il tempo è scaduto!");
                navigator.goToHome();
            }
        }, 600000);
    }
}



