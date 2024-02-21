package view2.user;

import com.ispw.progetto.bean.SignUpUserBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.exception.ExistsUserException;
import com.ispw.progetto.exception.PlacesTerminatedException;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;


public class InfoUserCLI {
    private final UserBean currentUser;

    public InfoUserCLI(UserBean currentUser) {
        this.currentUser = currentUser;
    }

    public void start(HomeLoginCLI homeLoginCLI) throws SQLException, ExistsUserException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(currentUser);
        SignUpUserBean user=regLoginControllerApp.info();
        Printer.printMessage("Nome: "+user.getNome());
        Printer.printMessage("Cognome: "+user.getCognome());
        Printer.printMessage("Username: "+user.getUsername());
        Printer.printMessage("Email: "+user.getEmail());
        homeLoginCLI.start();

    }
}
