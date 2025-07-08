module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    requires java.rmi;


    opens com.ispw.progetto to javafx.fxml;
    requires com.opencsv;
    exports com.ispw.progetto;
    exports com.ispw.progetto.controller_graf;
    exports com.ispw.progetto.bean;
    opens com.ispw.progetto.controller_graf to javafx.fxml;
    exports com.ispw.progetto.controller_graf.agenzia;
    opens com.ispw.progetto.controller_graf.agenzia to javafx.fxml;
    exports com.ispw.progetto.controller_graf.utente;
    opens com.ispw.progetto.controller_graf.utente to javafx.fxml;
    exports viewseconda.agency;
    opens viewseconda.agency to javafx.fxml;
    exports com.ispw.progetto.pattern.factory;
    exports com.ispw.progetto.controller_app;
    exports com.ispw.progetto.exception;
    exports com.ispw.progetto.utils;

}