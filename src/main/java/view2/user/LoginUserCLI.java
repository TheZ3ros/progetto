package view2.user;

import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.exception.PlacesTerminatedException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginUserCLI {

    public void login() throws PlacesTerminatedException, AlreadyPrenotedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserire username");
        String username = scanner.nextLine();
        System.out.println("inserire password");
        String password= scanner.nextLine();
        UserBean userBean=new UserBean(username, password);

        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(userBean);
            regLoginControllerApp.loginUtente();
            System.out.println("login avvenuto con successo");
            HomeLoginCLI homeLoginCLI=new HomeLoginCLI(userBean);
            homeLoginCLI.start();

        }
        catch(CredentialErrorException e){
            System.out.println(e.getMessage());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
