package view2.agency;

import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.pattern.factory.BeanFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AgencyTripsCLI {
    private final BeanFactory currentAgency;

    public AgencyTripsCLI(BeanFactory currentAgency){this.currentAgency=currentAgency;}

    public void start(HomeAgencyCLI home) throws SQLException, IOException, NotValidCouponException {
        Scanner scanner = new Scanner(System.in);
        BookTripController bookTripController=new BookTripController();
        List<TripBean> viaggi = bookTripController.showTrip();
        for(TripBean viaggio : viaggi){
            String nome = viaggio.getCity();
            Printer.printMessage("/////////////////////////");
            Printer.printMessage("Città: "+nome);
            int id = viaggio.getId();
            Printer.printMessage("ID: "+id);
        }
        Printer.printMessage("Seleziona l'id del viaggio di cui vuoi visualizzare lo stato: ");
        int id = scanner.nextInt();
        TripStatusCLI tripStatusCLI = new TripStatusCLI(currentAgency);
        tripStatusCLI.start(home,id);
        home.start();
    }

}
