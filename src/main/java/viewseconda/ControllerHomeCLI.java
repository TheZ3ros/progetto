package viewseconda;

import viewseconda.agency.LoginAgencyCLI;
import viewseconda.user.LoginUserCLI;

import java.util.Scanner;

public class ControllerHomeCLI {
public void start() throws Exception {
    Printer.printMessage("Scegliere l'operazione da eseguire");
    Printer.printMessage("1-Registrazione");
    Printer.printMessage("2-Login come utente");
    Printer.printMessage("3-Login come azienda");
    Scanner reader = new Scanner(System.in);
    int n;
    boolean continua = true;

    while (continua) {
        n = reader.nextInt();
        switch (n) {
            case 1:
                RegistrazioneUserCLI registrazioneUserCLI = new RegistrazioneUserCLI();
                registrazioneUserCLI.start(this);
                break;
            case 2:
                LoginUserCLI loginUserCLI = new LoginUserCLI();
                loginUserCLI.login();
                break;
            case 3:
                LoginAgencyCLI loginAgencyCLI = new LoginAgencyCLI();
                loginAgencyCLI.login();
                break;
            default:
                Printer.printMessage("Inserire un'opzione valida");
                break;
        }

        Printer.printMessage("Vuoi continuare? (S/N)");
        String risposta = reader.next();
        continua = risposta.equalsIgnoreCase("s");
    }
        }

    }


