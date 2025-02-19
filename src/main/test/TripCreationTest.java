import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.controller_app.CreateTripController;
import com.ispw.progetto.controller_graf.agenzia.ViewTripCreationController;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripCreationTest {
    /*
    Author of the test: Podda Alessandro
    MATRICOLA: 0316851

     */
        @BeforeAll
        public static void initJFX() {
            // Inizializza JavaFX
            new JFXPanel();
        }

    @Test
    public void tripcreationtest() throws IOException, SQLException, ParseException {
        String city="Vienna";
        int available=100;
        SimpleDateFormat converter = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date dataAnd;
        dataAnd = new java.sql.Date(converter.parse("2024/09/10").getTime());
        java.sql.Date dataRit;
        dataRit = new java.sql.Date(converter.parse("2024/09/17").getTime());
        float price=1050;
        String path="C:/Users/Proprietario/Desktop/java/progetto/src/main/resources/com/ispw/progetto/css/immagini/vienna.jpg";
        Image image=new Image("file:"+path);
        ViewTripCreationController tripCreationController = new ViewTripCreationController();
        byte[] imageBytes = tripCreationController.imageToBytes(image);
        TripBean trip = new TripBean(city, available, dataAnd, dataRit, price, imageBytes);
        CreateTripController creation = new CreateTripController(trip);
        creation.uploadTrip();

        BookTripController bookTripController = new BookTripController();
        List<TripBean> trips = bookTripController.showTrip();
        TripBean giusto = trips.get(trips.size()-1);
        assertEquals(giusto.getPrice(),trip.getPrice());
        assertEquals(giusto.getAvailable(),trip.getAvailable());
        assertEquals(giusto.getDataAnd(), trip.getDataAnd());
        assertEquals(giusto.getDataRit(), trip.getDataRit());

    }




}
