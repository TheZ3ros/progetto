package view2.user;

import com.example.progetto.bean.SignUpUserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.PlacesTerminatedException;
import com.example.progetto.pattern.factory.BeanFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;


public class InfoUserCLI {
    private final BeanFactory currentUser;

    public InfoUserCLI(BeanFactory currentUser) {
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
