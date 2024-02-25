package viewseconda.agency;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.exception.CredentialErrorException;
import com.ispw.progetto.exception.NotValidCouponException;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginAgencyCLI {
    public void login()  {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("inserire username");
        String username = scanner.nextLine();
        Printer.printMessage("inserire password");
        String password= scanner.nextLine();
        AgencyBean agencyBean=new AgencyBean();
        agencyBean.setUsername(username);
        agencyBean.setPassword(password);

        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(agencyBean);
            regLoginControllerApp.loginAgenzia();
            Printer.printMessage("login avvenuto con successo");
            HomeAgencyCLI homeAgency=new HomeAgencyCLI(agencyBean);
            homeAgency.start();

        }
        catch(CredentialErrorException | SQLException | IOException | NotValidCouponException e){
            Printer.printMessage(e.getMessage());
        }
    }
}
