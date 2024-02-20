import com.example.progetto.bean.BuonoBean;
import com.example.progetto.controller_app.PagamentoControllerApp;
import com.example.progetto.exception.NotValidCouponException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;
/** Author of the test:  Luca Lo Mastro
 *                 Matricola 0308587
 */

/**
Il seguente test verifica se l'applicazione del coupon funziona e se preleva esattamente il valore del coupon
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