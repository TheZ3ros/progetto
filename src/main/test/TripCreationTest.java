import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.controller_app.CreateTripController;
import com.ispw.progetto.controller_graf.agenzia.ViewTripCreationController;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripCreationTest {
    /*
    Authore of the test: Podda Alessandro
    MATRICOLA: 0316851

     */

    @Test
    public void tripcreationtest() throws IOException, SQLException {
        String city="Vienna";
        int available=100;
        Date dataAnd = Date.valueOf("2025/09/08");
        Date dataRit = Date.valueOf("2026/08/06");
        float price=1050;
        String path="C:/Users/AsD/IdeaProjects/ipsw/src/main/resources/com/example/progetto/css/immagini/stoccolma.jpg";
        Image image=new Image("file: "+path);
        ViewTripCreationController tripCreationController = new ViewTripCreationController();
        byte[] imageBytes = tripCreationController.imageToBytes(image);

        TripBean trip = new TripBean(city, available, dataAnd, dataRit, price, imageBytes);
        CreateTripController creation = new CreateTripController(trip);
        creation.uploadTrip();

        BookTripController bookTripController = new BookTripController();
        List<TripBean> trips = bookTripController.showTrip();
        TripBean giusto = trips.get(5);
        assertEquals(giusto.getPrice(),trip.getPrice());
        assertEquals(giusto.getAvailable(),trip.getAvailable());
        assertEquals(giusto.getId(),trip.getId());
        assertEquals(giusto.getDataAnd(), trip.getDataAnd());
        assertEquals(giusto.getDataRit(), trip.getDataRit());
    }




}
