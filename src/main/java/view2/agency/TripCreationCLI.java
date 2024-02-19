package view2.agency;

import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.CreateTripController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.example.progetto.controller_graf.agenzia.ViewTripCreationController;
import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.pattern.Factory.BeanFactory;
import javafx.scene.image.Image;

public class TripCreationCLI {
    private final BeanFactory currentAgency;

    public TripCreationCLI(BeanFactory currentAgency) {
        this.currentAgency = currentAgency;
    }
    public void start(HomeAgencyCLI home) throws SQLException, IOException, NotValidCouponException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il nome della città: ");
        String city = scanner.nextLine();
        System.out.println("Inserisci i posti totali disponibili: ");
        int available = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Inserisci data di partenza in formato aaaa/mm/gg: ");
        String inputAnd = scanner.nextLine();
        SimpleDateFormat converter = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date dataAnd = null;
        try {
            dataAnd = new java.sql.Date(converter.parse(inputAnd).getTime());
            System.out.println("Data inserita: " + dataAnd);
        } catch (ParseException e) {
            System.err.println("Formato data non valido. Assicurati di inserire la data nel formato specificato.");
        }

        System.out.println("Inserisci data di ritorno in formato aaaa/mm/gg: ");
        String inputRit = scanner.nextLine();
        java.sql.Date dataRit = null;
        try {
            dataRit = new java.sql.Date(converter.parse(inputRit).getTime());
            System.out.println("Data inserita: " + dataRit);
        } catch (ParseException e) {
            System.err.println("Formato data non valido. Assicurati di inserire la data nel formato specificato.");
        }
        System.out.println("Inserisci il prezzo dell'itinerario: ");
        float price = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Inserisci il percorso dell'immagine relativa all'itinerario: ");
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
