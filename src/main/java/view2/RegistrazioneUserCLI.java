package view2;

import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.PasswordIllegalException;
import com.example.progetto.exception.PlacesTerminatedException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class RegistrazioneUserCLI {
    public void start(ControllerHomeCLI home) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire un nuovo username");
        String username = scanner.nextLine();
        System.out.println("Inserire una nuova password");
        String password = scanner.nextLine();
        UserBean newUser = new UserBean(username, password);
        RegLoginControllerApp regLoginControllerApp = new RegLoginControllerApp(newUser);
        try {
            regLoginControllerApp.registrazione();
            System.out.println("La registrazione è avvenuta con successo");
            home.start();
        }
        catch(PasswordIllegalException | PlacesTerminatedException | AlreadyPrenotedException e){
            System.out.println("La password deve essere di almeno 8 caratteri");
        } catch (ExistsUserException e) {
            System.out.println(e.getMessage());
        }
    }

    }


