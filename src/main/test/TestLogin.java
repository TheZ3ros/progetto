import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.pattern.factory.EntityFactory;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
/**Author of the test:  Luca Lo Mastro
 *                  Matricola 0308587
 */

/**
 Il seguente test verifica se il login è effettuato correttamente.
 */
public class TestLogin{

    @Test
public void testLogin() throws Exception {
        UserBean userBean=new UserBean();
        userBean.setPassword("aa");
        userBean.setUsername("aa");

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
