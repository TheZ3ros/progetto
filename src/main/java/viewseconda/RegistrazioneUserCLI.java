package viewseconda;

import com.ispw.progetto.bean.SignUpUserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.exception.ExistsUserException;
import com.ispw.progetto.exception.PasswordIllegalException;

import java.util.Scanner;


public class RegistrazioneUserCLI {
    public boolean start() {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Inserire un nuovo username");
        String username = scanner.nextLine();
        Printer.printMessage("Inserire nome");
        String nome = scanner.nextLine();
        Printer.printMessage("Inserire cognome");
        String cognome = scanner.nextLine();
        Printer.printMessage("Inserire email");
        String email = scanner.nextLine();
        Printer.printMessage("Inserire una nuova password");
        String password = scanner.nextLine();

        SignUpUserBean signUpUserBean = new SignUpUserBean();
        signUpUserBean.setUsername(username);
        signUpUserBean.setNome(nome);
        signUpUserBean.setCognome(cognome);
        signUpUserBean.setPassword(password);
        signUpUserBean.setEmail(email);

        RegLoginControllerApp regLoginControllerApp = new RegLoginControllerApp(signUpUserBean);

        try {
            regLoginControllerApp.registrazione();
            Printer.printMessage("La registrazione è avvenuta con successo");
            return true;
        } catch (PasswordIllegalException e) {
            Printer.printMessage("La password deve essere di almeno 8 caratteri");
        } catch (ExistsUserException e) {
            Printer.printMessage(e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        return false;
    }
}
