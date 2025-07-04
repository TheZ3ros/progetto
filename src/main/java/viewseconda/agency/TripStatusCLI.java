package viewseconda.agency;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripStatusBean;
import com.ispw.progetto.controller_app.TripStatusController;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripStatusCLI {
    protected final AgencyBean currentAgency;

    public TripStatusCLI(AgencyBean currentAgency) {
        this.currentAgency = currentAgency;
    }

    public void start(int id) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            List<TripStatusBean> stati = TripStatusController.showtripstatus(id);

            if (stati.isEmpty()) {
                Printer.printMessage("Nessuna prenotazione trovata per questo viaggio.");
                return;
            }

            for (TripStatusBean stato : stati) {
                Printer.printMessage("_________________________");
                Printer.printMessage("Username: " + stato.getUsername());
                Printer.printMessage("Stato: " + stato.isStatus());
            }

            Printer.printMessage("Inserisci lo username da confermare (invio per uscire): ");
            String user = scanner.nextLine().trim();

            if (user.isEmpty()) {
                continua = false;
            } else {
                TripStatusController statusUpdater = new TripStatusController();
                boolean updated = statusUpdater.updatetripstatus(id, user);

                if (updated) {
                    Printer.printMessage("Stato aggiornato con successo.");
                } else {
                    Printer.printMessage("Errore durante l'aggiornamento.");
                }

                // Ri-stampa la lista aggiornata e continua il ciclo
            }
        }
    }
}
