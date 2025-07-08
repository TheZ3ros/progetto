import com.ispw.progetto.bean.SearchBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.exception.FailedSearchException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {
    /** Author of the test: Alessandro Podda
     *                 Matricola 0316851
     Il seguente test verifica che la ricerca di un viaggio tramite nome della città funzioni correttamente
     */
    @Test
    public void searchtest() throws SQLException, FailedSearchException, IOException {
        String citta = "Roma";
        SearchBean searchBean = new SearchBean();
        searchBean.setCitta(citta);
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi;
        viaggi = bookTripController.searchByCity(searchBean);
        for(TripBean viaggio : viaggi){

            assertEquals(viaggio.getCity(),searchBean.getCitta());

        }


    }
}
