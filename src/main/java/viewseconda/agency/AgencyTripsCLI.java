package viewseconda.agency;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.exception.NotValidCouponException;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AgencyTripsCLI {
    private final AgencyBean currentAgency;

    public AgencyTripsCLI(AgencyBean currentAgency) {
        this.currentAgency = currentAgency;
    }

    public void start() throws SQLException, IOException, NotValidCouponException {
        Scanner scanner = new Scanner(System.in);
        BookTripController bookTripController = new BookTripController();

        List<TripBean> viaggi = bookTripController.showTrip();
        if (viaggi.isEmpty()) {
            Printer.printMessage("Non ci sono viaggi disponibili.");
            return;
        }

        for (TripBean viaggio : viaggi) {
            Printer.printMessage("/////////////////////////");
            Printer.printMessage("Città: " + viaggio.getCity());
            Printer.printMessage("ID: " + viaggio.getId());
        }

        Printer.printMessage("Seleziona l'id del viaggio di cui vuoi visualizzare lo stato: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // evita problemi con nextInt()

        TripStatusCLI tripStatusCLI = new TripStatusCLI(currentAgency);
        tripStatusCLI.start(id);
    }
}
