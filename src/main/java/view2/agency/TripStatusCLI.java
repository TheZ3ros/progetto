package view2.agency;

import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.TripStatusBean;
import com.example.progetto.controller_app.GetTripStatusController;
import com.example.progetto.controller_graf.agenzia.TripStatusController;
import com.example.progetto.exception.NotValidCouponException;
import view2.agency.HomeAgencyCLI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripStatusCLI {
    private final AgencyBean currentAgency;

    public TripStatusCLI(AgencyBean currentAgency){this.currentAgency=currentAgency;}

    public void start(HomeAgencyCLI home,int id) throws SQLException, IOException, NotValidCouponException {

        String username;
        boolean state;

        List<TripStatusBean> stati = GetTripStatusController.showtripstatus(id);
        for (TripStatusBean stato : stati){
            username = stato.getUsername();
            state = stato.isStatus();
            System.out.println("_________________________");
            System.out.println("Username: "+username);
            System.out.println("Stato: "+state);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire lo username di cui confermare la prenotazione (non scrivere nulla se non si desidera fare nulla): ");
        String user = scanner.nextLine();
        if(user.isEmpty()){
            home.start();
        } //verificare se sia meglio sostituire questo if con un ciclo while
        else{
            GetTripStatusController statusupdater = new GetTripStatusController();
            boolean b = statusupdater.updatetripstatus(id,user);
            if (b){
                System.out.println("Stato aggiornato");
                start(home,id);
            }
            else{
                System.out.println("Query non eseguita");
                home.start();
            }
        }



    }
}
