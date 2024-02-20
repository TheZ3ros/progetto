module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    requires java.rmi;


    opens com.example.progetto to javafx.fxml;
    requires com.opencsv;
    requires org.junit.jupiter.api;
    exports com.example.progetto;
    exports com.example.progetto.controller_graf;
    exports com.example.progetto.bean;
    opens com.example.progetto.controller_graf to javafx.fxml;
    exports com.example.progetto.controller_graf.agenzia;
    opens com.example.progetto.controller_graf.agenzia to javafx.fxml;
    exports com.example.progetto.controller_graf.utente;
    opens com.example.progetto.controller_graf.utente to javafx.fxml;
    exports view2.agency;
    opens view2.agency to javafx.fxml;
    exports com.example.progetto.pattern.factory;
    exports com.example.progetto.controller_app;
}