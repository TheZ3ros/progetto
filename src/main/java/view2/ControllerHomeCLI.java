package view2;

import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.PlacesTerminatedException;
import view2.user.LoginUserCLI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ControllerHomeCLI {
public void start() throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
    Printer.printMessage("Scegliere l'operazione da eseguire");
    Printer.printMessage("1-Registrazione");
    Printer.printMessage("2-Login come utente");
    Printer.printMessage("3-Login come azienda");
    Scanner reader = new Scanner(System.in);
    int n;
    while (true) {
        n = reader.nextInt();
        switch (n) {
            case 1:
                RegistrazioneUserCLI registrazioneUserCLI=new RegistrazioneUserCLI();
                registrazioneUserCLI.start(this);
            case 2:
                LoginUserCLI loginUserCLI=new LoginUserCLI();
                loginUserCLI.login();
            case 3:
            default:
                Printer.printMessage("inserire un'opzione valida");
        }
        }

    }
    }

