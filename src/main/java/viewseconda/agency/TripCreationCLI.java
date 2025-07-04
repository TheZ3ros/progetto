package viewseconda.agency;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.CreateTripController;
import com.ispw.progetto.controller_graf.agenzia.ViewTripCreationController;
import com.ispw.progetto.exception.NotValidCouponException;
import javafx.scene.image.Image;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TripCreationCLI {
    protected final AgencyBean currentAgency;

    public TripCreationCLI(AgencyBean currentAgency) {
        this.currentAgency = currentAgency;
    }

    public boolean start() throws SQLException, IOException, NotValidCouponException {
        Scanner scanner = new Scanner(System.in);

        Printer.printMessage("Inserisci il nome della città: ");
        String city = scanner.nextLine();

        Printer.printMessage("Inserisci i posti totali disponibili: ");
        int available = scanner.nextInt();
        scanner.nextLine();

        Printer.printMessage("Inserisci data di partenza in formato aaaa/mm/gg: ");
        String inputAnd = scanner.nextLine();
        java.sql.Date dataAnd;
        try {
            dataAnd = new java.sql.Date(new SimpleDateFormat("yyyy/MM/dd").parse(inputAnd).getTime());
            Printer.printMessage("Data inserita: " + dataAnd);
        } catch (ParseException e) {
            Printer.printMessage("Formato data non valido.");
            return false;
        }

        Printer.printMessage("Inserisci data di ritorno in formato aaaa/mm/gg: ");
        String inputRit = scanner.nextLine();
        java.sql.Date dataRit;
        try {
            dataRit = new java.sql.Date(new SimpleDateFormat("yyyy/MM/dd").parse(inputRit).getTime());
            Printer.printMessage("Data inserita: " + dataRit);
        } catch (ParseException e) {
            Printer.printMessage("Formato data non valido.");
            return false;
        }

        Printer.printMessage("Inserisci il prezzo dell'itinerario: ");
        float price = scanner.nextFloat();
        scanner.nextLine();

        Printer.printMessage("Inserisci il percorso dell'immagine relativa all'itinerario: ");
        String path = scanner.nextLine();

        Image image = new Image("file:" + path);
        ViewTripCreationController tripCreationController = new ViewTripCreationController();
        byte[] imageBytes = tripCreationController.imageToBytes(image);

        TripBean trip = new TripBean(city, available, dataAnd, dataRit, price, imageBytes);
        CreateTripController creation = new CreateTripController(trip);
        creation.uploadTrip();

        Printer.printMessage("Viaggio creato con successo.");
        return true;
    }
}
