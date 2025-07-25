import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.utils.AppContext;
import com.ispw.progetto.utils.PersistenceMode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
/** Author of the test: Alessandro Podda
 *                 Matricola 0316851
 Il seguente test verifica che il metodo showtrip esegua correttamente l'interrogazione
 al database per ottenere le informazioni su un viaggio
 */
public class BookingTest {
    @Test
    public void bookingTest() throws ParseException, SQLException, IOException {
        AppContext appContext = AppContext.getInstance();
        appContext.setPersistenceMode(PersistenceMode.DB);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateand = dateFormat.parse("2024-02-18");
        Date daterit = dateFormat.parse("2024-03-01");
        java.sql.Date sqlDateAnd = new java.sql.Date(dateand.getTime());
        java.sql.Date sqlDateRit = new java.sql.Date(daterit.getTime());
        TripBean trip = new TripBean(1,"Madrid", 5655, sqlDateAnd, sqlDateRit, 457F);
        BookTripController bookTripController = new BookTripController();
        List<TripBean> trips = bookTripController.showTrip();
        TripBean giusto = trips.get(0);
        assertEquals(giusto.getPrice(),trip.getPrice());
        assertEquals(giusto.getAvailable(),trip.getAvailable());
        assertEquals(giusto.getId(),trip.getId());
        assertEquals(giusto.getDataAnd(), trip.getDataAnd());
        assertEquals(giusto.getDataRit(), trip.getDataRit());
    }
}