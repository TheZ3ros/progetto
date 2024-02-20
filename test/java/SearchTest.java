import com.example.progetto.bean.SearchBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.FailedSearchException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {
    /*
    * Test author: Podda Alessandro
    * MATRICOLA: 0316851
    * */

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
