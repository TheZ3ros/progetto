package view2;

import com.example.progetto.bean.SignUpUserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.PasswordIllegalException;
import com.example.progetto.exception.PlacesTerminatedException;

import java.util.Scanner;


public class RegistrazioneUserCLI {
    public void start(ControllerHomeCLI home) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Inserire un nuovo username");
        String username = scanner.nextLine();
       Printer.printMessage("Inserire nome");
        String nome = scanner.nextLine();
        Printer.printMessage("Inserire cognome");
        String cognome = scanner.nextLine();
        Printer.printMessage("Inserire email");
        String email = scanner.nextLine();
        Printer.printMessage("Inserire una nuova password");
        String password = scanner.nextLine();
        SignUpUserBean signUpUserBean=new SignUpUserBean();
        signUpUserBean.setUsername(username);
        signUpUserBean.setNome(nome);
        signUpUserBean.setCognome(cognome);
        signUpUserBean.setPassword(password);
        signUpUserBean.setEmail(email);
        RegLoginControllerApp regLoginControllerApp = new RegLoginControllerApp(signUpUserBean);
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


