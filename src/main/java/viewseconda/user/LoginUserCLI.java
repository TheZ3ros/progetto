package viewseconda.user;

import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.exception.CredentialErrorException;
import com.ispw.progetto.utils.AppContext;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginUserCLI {

    private final AppContext appContext;

    public LoginUserCLI(AppContext appContext) {
        this.appContext = appContext;
    }

    public void login() throws CredentialErrorException {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("inserire username");
        String username = scanner.nextLine();
        Printer.printMessage("inserire password");
        String password = scanner.nextLine();

        UserBean userBean = new UserBean();
        userBean.setPassword(password);
        userBean.setUsername(username);

        try {
            RegLoginControllerApp regLoginControllerApp = new RegLoginControllerApp(userBean);
            regLoginControllerApp.loginUtente();
            Printer.printMessage("login avvenuto con successo");

            HomeLoginCLI homeLoginCLI = new HomeLoginCLI(userBean, appContext); // 🔹 passa il contesto
            homeLoginCLI.start();

        } catch (CredentialErrorException e) {
            Printer.printMessage(e.getMessage());
        } catch (SQLException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

