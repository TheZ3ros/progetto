package com.ispw.progetto.controller_graf;

import com.ispw.progetto.bean.SignUpUserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.exception.ExistsUserException;
import com.ispw.progetto.exception.PasswordIllegalException;
import com.ispw.progetto.exception.SQLStatementException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrazioneController {

    @FXML
    private TextField usernameUtente;
    @FXML
    private PasswordField passwordUtente;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField email;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void vaiAHome() throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/home.fxml"));
        Parent homeRoot = homeLoader.load();
        Scene homeScene = new Scene(homeRoot);
        HomeController homeController = homeLoader.getController();
        homeController.setStage(stage);  // importante: passare lo stage!
        stage.setScene(homeScene);
        stage.setTitle("Home");
    }

    @FXML
    public void registrazioneutente() throws SQLException, SQLStatementException, IOException {
        String userUtente = usernameUtente.getText();
        String passUtente = passwordUtente.getText();
        String nomeUser = nome.getText();
        String cognomeUser = cognome.getText();
        String emailUser = email.getText();

        if (userUtente.isEmpty() || passUtente.isEmpty() || nomeUser.isEmpty() || cognomeUser.isEmpty() || emailUser.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registrazione");
            alert.setHeaderText(null);
            alert.setContentText("Completa tutti i campi");
            alert.showAndWait();
            return;
        }

        SignUpUserBean signUpUserBean = new SignUpUserBean();
        signUpUserBean.setCognome(cognomeUser);
        signUpUserBean.setEmail(emailUser);
        signUpUserBean.setNome(nomeUser);
        signUpUserBean.setUsername(userUtente);
        signUpUserBean.setPassword(passUtente);

        try {
            RegLoginControllerApp reg = new RegLoginControllerApp(signUpUserBean);
            reg.registrazione();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registrazione");
            alert.setHeaderText(null);
            alert.setContentText("Registrazione avvenuta con successo");
            alert.showAndWait();
        } catch (PasswordIllegalException | ExistsUserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registrazione fallita");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void vaiALogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        LoginController controller = loader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.setTitle("Accedi");
    }
}
