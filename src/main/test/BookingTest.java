import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
/** Author of the test:  Luca Lo Mastro
 *                 Matricola 0308587
 */

/**
 Il seguente test verifica che il metodo showtrip esegua correttamente l'interrogazione
 al database per le informazioni su un viaggio
 */
public class BookingTest {
    @Test
    public void bookinTest() throws ParseException, SQLException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateand = dateFormat.parse("2024-03-11");
        Date daterit = dateFormat.parse("2024-03-18");
        java.sql.Date sqlDateAnd = new java.sql.Date(dateand.getTime());
        java.sql.Date sqlDateRit = new java.sql.Date(daterit.getTime());
        TripBean trip = new TripBean(3,"Parigi", 0, sqlDateAnd, sqlDateRit, 700F);
        BookTripController bookTripController = new BookTripController();
        List<TripBean> trips = bookTripController.showTrip();
        TripBean giusto = trips.get(2);
        assertEquals(giusto.getPrice(),trip.getPrice());
        assertEquals(giusto.getAvailable(),trip.getAvailable());
        assertEquals(giusto.getId(),trip.getId());
        assertEquals(giusto.getDataAnd(), trip.getDataAnd());
        assertEquals(giusto.getDataRit(), trip.getDataRit());

    }

}