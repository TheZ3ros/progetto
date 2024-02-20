import com.example.progetto.bean.TripStatusBean;
import com.example.progetto.controller_app.GetTripStatusController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusVisualizerTest {
    /*
    * Author of the test: Podda Alessandro
    * MATRICOLA: 0316851
    * */
    @Test
    public void statusvisualizertest() throws SQLException, IOException {
        int id =3;
        String username="";
        boolean state=false;
        String testusername="bb";
        boolean teststate=false;
        List<TripStatusBean> stati = GetTripStatusController.showtripstatus(id);
        for (TripStatusBean stato : stati) {
            username = stato.getUsername();
            state = stato.isStatus();
        }
        assertEquals(username,testusername);
        assertEquals(state,teststate);
    }
}