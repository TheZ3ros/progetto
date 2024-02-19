package view2.user;

import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.pattern.factory.BeanFactory;
import com.example.progetto.pattern.factory.Factory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginUserCLI {

    public void login() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("inserire username");
        String username = scanner.nextLine();
        Printer.printMessage("inserire password");
        String password= scanner.nextLine();
        Factory factory=new Factory();
        BeanFactory userBean=factory.createBean(1);
        userBean.setPassword(password);
        userBean.setUsername(username);

        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(userBean);
            regLoginControllerApp.loginUtente();
            Printer.printMessage("login avvenuto con successo");
            HomeLoginCLI homeLoginCLI=new HomeLoginCLI(userBean);
            homeLoginCLI.start();

        }
        catch(CredentialErrorException e){
            Printer.printMessage(e.getMessage());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
