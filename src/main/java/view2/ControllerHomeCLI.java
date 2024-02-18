package view2;

import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.PlacesTerminatedException;
import view2.agency.LoginAgencyCLI;
import view2.user.LoginUserCLI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ControllerHomeCLI {
public void start() throws PlacesTerminatedException, AlreadyPrenotedException, SQLException, IOException {
    System.out.println("Scegliere l'operazione da eseguire");
    System.out.println("1-Registrazione");
    System.out.println("2-Login come utente");
    System.out.println("3-Login come azienda");
    Scanner reader = new Scanner(System.in);
    int n;
    while (true) {
        n = reader.nextInt();
        switch (n) {
            case 1:
                RegistrazioneUserCLI registrazioneUserCLI = new RegistrazioneUserCLI();
                registrazioneUserCLI.start(this);
            case 2:
                LoginUserCLI loginUserCLI = new LoginUserCLI();
                loginUserCLI.login();
            case 3:
                LoginAgencyCLI loginAgencyCLI = new LoginAgencyCLI();
                loginAgencyCLI.login();
            default:
                System.out.println("inserire un'opzione valida");
        }
        }

    }
    }

