import com.ispw.progetto.bean.BuonoBean;
import com.ispw.progetto.controller_app.PagamentoControllerApp;
import com.ispw.progetto.exception.NotValidCouponException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;

/** Author of the test: Alessandro Podda
 *                 Matricola 0316851
Il seguente test verifica se l'applicazione del coupon funziona e se preleva esattamente il suo valore
 */
public class CouponTest{

    @Test
    public void ViewTripTest() {
        BuonoBean buono=new BuonoBean();
        int flag=1;
        buono.setValore(150);
        buono.setCodice("sconto");
        BuonoBean buonoGiusto=null;
        PagamentoControllerApp pagamentoControllerApp=new PagamentoControllerApp();
        try {
            buonoGiusto=pagamentoControllerApp.checkBuono(buono);
        } catch (SQLException | IOException | NotValidCouponException e) {
            flag=0;
        }
        assert buonoGiusto != null;
        assertEquals(buonoGiusto.getValore(),buono.getValore());
        assertEquals(1,flag);

    }
}