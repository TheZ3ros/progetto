module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.progetto to javafx.fxml;
    exports com.example.progetto;
}