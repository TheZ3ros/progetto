package viewseconda;

import com.ispw.progetto.exception.CredentialErrorException;
import viewseconda.agency.LoginAgencyCLI;
import viewseconda.user.LoginUserCLI;

import java.util.Scanner;

public class ControllerHomeCLI {

    public void start() throws CredentialErrorException {
        Scanner reader = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            Printer.printMessage("Scegliere l'operazione da eseguire");
            Printer.printMessage("1 - Registrazione");
            Printer.printMessage("2 - Login come utente");
            Printer.printMessage("3 - Login come azienda");

            int n = reader.nextInt();
            reader.nextLine();

            switch (n) {
                case 1:
                    new RegistrazioneUserCLI().start(); // Se serve, puoi anche passare appContext
                    break;
                case 2:
                    new LoginUserCLI().login(); // 🔹 passaggio del contesto
                    break;
                case 3:
                    new LoginAgencyCLI().login(); // 🔹 se supportato
                    break;
                default:
                    Printer.printMessage("Inserire un'opzione valida");
            }

            Printer.printMessage("Vuoi continuare? (S/N)");
            String risposta = reader.nextLine();
            if (!risposta.equalsIgnoreCase("s")) {
                continua = false;
            }
        }
    }
}
