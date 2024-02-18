package view2.user;

import com.example.progetto.bean.UserBean;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.PlacesTerminatedException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeLoginCLI {
    private final UserBean currentUser;


    public HomeLoginCLI(UserBean currentUser) {
        this.currentUser = currentUser;
    }
    public void start() throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        System.out.println("Scegliere l'operazione da eseguire");
        System.out.println("1-Prenota un nuovo viaggio");
        System.out.println("2-Visualizza stato dei viaggi prenotati");
        Scanner reader = new Scanner(System.in);
        int n;
        while (true) {
            n = reader.nextInt();
            switch (n) {
                case 1:
                    TripViewCLI tripViewCLI=new TripViewCLI(currentUser);
                    tripViewCLI.start(this);
                    break;
                case 2:
                    BookedTripCLI bookedTripCLI=new BookedTripCLI(currentUser);
                    bookedTripCLI.start(this);
                    break;
                default:
                    System.out.println("inserire un'opzione valida");
            }
        }
    }
}
