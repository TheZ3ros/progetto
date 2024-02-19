package view2;

import view2.agency.LoginAgencyCLI;
import view2.user.LoginUserCLI;

import java.util.Scanner;

public class ControllerHomeCLI {
public void start() throws Exception {
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
                System.out.println("inserire un'opzione valida");
        }
        }

    }
    }

