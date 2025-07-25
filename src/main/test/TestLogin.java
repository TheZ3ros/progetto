import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.exception.CredentialErrorException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/** Author of the test: Alessandro Podda
 *                      Matricola: 0316851
 Il seguente test verifica se il login è effettuato correttamente.
 */
public class TestLogin{

    @Test
public void testLogin() throws Exception {
        UserBean userBean=new UserBean();
        userBean.setPassword("alessandro");
        userBean.setUsername("alessandro");

        int flag = 1;
        RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(userBean);
        try {
        regLoginControllerApp.loginUtente();

        } catch (CredentialErrorException e) {
                flag = -1;
        }
        assertEquals(1, flag);
        }


        }
