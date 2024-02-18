package view2.agency;

import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.exception.NotValidCouponException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class AgencyUserCLI {

    public void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserire username");
        String username = scanner.nextLine();
        System.out.println("inserire password");
        String password= scanner.nextLine();
        AgencyBean agencyBean=new AgencyBean(username, password);

        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(agencyBean);
            regLoginControllerApp.loginAgenzia();
            System.out.println("login avvenuto con successo");
            HomeAgencyCLI homeAgencyCLI = new HomeAgencyCLI(agencyBean);
            homeAgencyCLI.start();

        }
        catch(CredentialErrorException e){
            System.out.println(e.getMessage());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (NotValidCouponException e) {
            throw new RuntimeException(e);
        }
    }
}
