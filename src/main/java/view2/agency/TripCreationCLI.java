package view2.agency;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.CreateTripController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.ispw.progetto.controller_graf.agenzia.ViewTripCreationController;
import com.ispw.progetto.exception.NotValidCouponException;
import javafx.scene.image.Image;
import view2.Printer;

public class TripCreationCLI {
    protected final AgencyBean currentAgency;

    public TripCreationCLI(AgencyBean currentAgency) {
        this.currentAgency = currentAgency;
    }
    public void start(HomeAgencyCLI home) throws SQLException, IOException, NotValidCouponException {

        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Inserisci il nome della città: ");
        String city = scanner.nextLine();
        Printer.printMessage("Inserisci i posti totali disponibili: ");
        int available = scanner.nextInt();

        scanner.nextLine();

        Printer.printMessage("Inserisci data di partenza in formato aaaa/mm/gg: ");
        String inputAnd = scanner.nextLine();
        SimpleDateFormat converter = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date dataAnd = null;
        try {
            dataAnd = new java.sql.Date(converter.parse(inputAnd).getTime());
            Printer.printMessage("Data inserita: " + dataAnd);
        } catch (ParseException e) {
            Printer.printMessage("Formato data non valido. Assicurati di inserire la data nel formato specificato.");
        }

        Printer.printMessage("Inserisci data di ritorno in formato aaaa/mm/gg: ");
        String inputRit = scanner.nextLine();
        java.sql.Date dataRit = null;
        try {
            dataRit = new java.sql.Date(converter.parse(inputRit).getTime());
            Printer.printMessage("Data inserita: " + dataRit);
        } catch (ParseException e) {
            Printer.printMessage("Formato data non valido. Assicurati di inserire la data nel formato specificato.");
        }
        Printer.printMessage("Inserisci il prezzo dell'itinerario: ");
        float price = scanner.nextInt();

        scanner.nextLine();

        Printer.printMessage("Inserisci il percorso dell'immagine relativa all'itinerario: ");
        String path = scanner.nextLine();

        Image image = new Image("file:"+path);
        ViewTripCreationController tripCreationController = new ViewTripCreationController();
        byte[] imageBytes = tripCreationController.imageToBytes(image);

        TripBean trip = new TripBean(city, available, dataAnd, dataRit, price, imageBytes);
        CreateTripController creation = new CreateTripController(trip);
        creation.uploadTrip();
        home.start();
    }
}
