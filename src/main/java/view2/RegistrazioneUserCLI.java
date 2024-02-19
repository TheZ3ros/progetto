package view2;

import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.PasswordIllegalException;
import com.example.progetto.exception.PlacesTerminatedException;
import com.example.progetto.pattern.factory.BeanFactory;
import com.example.progetto.pattern.factory.Factory;

import java.util.Scanner;


public class RegistrazioneUserCLI {
    public void start(ControllerHomeCLI home) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Inserire un nuovo username");
        String username = scanner.nextLine();
       Printer.printMessage("Inserire una nuova password");
        String password = scanner.nextLine();
        Factory factory=new Factory();
        BeanFactory newUser=factory.createBean(1);
        newUser.setUsername(username);
        newUser.setPassword(password);
        RegLoginControllerApp regLoginControllerApp = new RegLoginControllerApp(newUser);
        try {
            regLoginControllerApp.registrazione();
            Printer.printMessage("La registrazione è avvenuta con successo");
            home.start();
        }
        catch(PasswordIllegalException | PlacesTerminatedException | AlreadyPrenotedException e){
            Printer.printMessage("La password deve essere di almeno 8 caratteri");
        } catch (ExistsUserException e) {
            Printer.printMessage(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    }


