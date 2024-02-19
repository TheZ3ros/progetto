package view2;


public class Printer {

    // Costruttore privato per evitare istanze esterne
    private Printer() {
    }
    public static void printMessage(String message) {
        System.out.println(message);
    }

}