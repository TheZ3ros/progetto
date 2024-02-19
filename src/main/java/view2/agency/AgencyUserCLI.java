package view2.agency;

import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.pattern.factory.BeanFactory;
import com.example.progetto.pattern.factory.Factory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class AgencyUserCLI {

    
    public void login() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("inserire username");
        String username = scanner.nextLine();
        Printer.printMessage("inserire password");
        String password= scanner.nextLine();
        Factory factory=new Factory();
        BeanFactory agencyBean= factory.createBean(2);
        agencyBean.setPassword(password);
        agencyBean.setUsername(username);

        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(agencyBean);
            regLoginControllerApp.loginAgenzia();
            Printer.printMessage("login avvenuto con successo");
            HomeAgencyCLI homeAgencyCLI = new HomeAgencyCLI(agencyBean);
            homeAgencyCLI.start();

        }
        catch(CredentialErrorException e){
            Printer.printMessage(e.getMessage());
        } catch (SQLException | IOException | NotValidCouponException e) {
            throw new RuntimeException(e);
        }
    }
}
