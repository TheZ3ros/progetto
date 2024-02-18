package view2.agency;

import com.example.progetto.bean.AgencyBean;
import view2.user.LoginUserCLI;

import java.util.Scanner;

public class HomeAgency {
    private final AgencyBean currentAgency;

    public HomeAgency(AgencyBean currentAgency) {
        this.currentAgency = currentAgency;
    }

    public void start(){
        System.out.println("Scegliere l'operazione da eseguire");
        System.out.println("1-Inserisci un nuovo viaggio");
        System.out.println("2-Controlla stato dei viaggi");
        Scanner reader = new Scanner(System.in);
        int n;
        while (true) {
            n = reader.nextInt();
            switch (n) {
                case 1:
                    //inserisci viaggio
                case 2:
                    //controlla stato dei viaggi
                default:
                    System.out.println("inserire un'opzione valida");
            }
        }

    }

    }

