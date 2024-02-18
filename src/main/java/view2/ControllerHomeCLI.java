package view2;

import view2.user.LoginUserCLI;

import java.util.Scanner;

public class ControllerHomeCLI {
public void start(){
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

            case 2:
                LoginUserCLI loginUserCLI=new LoginUserCLI();
                loginUserCLI.login();
            case 3:

            default:
                System.out.println("inserire un'opzione valida");
        }
        }

    }
    }

