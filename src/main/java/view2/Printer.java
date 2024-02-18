package view2;

import java.util.logging.Logger;

public class Printer {

    // Costruttore privato per evitare istanze esterne
    private Printer() {
    }
    public static void printMessage(String message) {
        System.out.println(message);
    }

}