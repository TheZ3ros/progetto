package com.ispw.progetto.controller_graf;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.controller_graf.agenzia.AgencyHomeController;
import com.ispw.progetto.controller_graf.utente.UserHomeController;
import com.ispw.progetto.exception.CredentialErrorException;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
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

public class LoginController implements StageAware {

    @FXML
    private TextField usernameUtente;

    @FXML
    private PasswordField passwordUtente;

    @FXML
    private TextField usernameAgenzia;

    @FXML
    private PasswordField passwordAgenzia;

    private Stage stage;


    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void vaiAHome() throws IOException {
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
    }

    @FXML
    public void registrati() throws IOException {
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/registrazione.fxml", this);
    }

    @FXML
    public void handlerloginutente() throws IOException, SQLException {
        String userUtente = usernameUtente.getText();
        String passUtente = passwordUtente.getText();

        UserBean user = new UserBean();
        user.setUsername(userUtente);
        user.setPassword(passUtente);

        RegLoginControllerApp login = new RegLoginControllerApp(user);

        try {
            login.loginUtente();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/utente/home_login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            UserHomeController controller = loader.getController();
            controller.setUser(user);
            controller.setStage(stage);
            controller.setButtonText();
            stage.setScene(scene);
            stage.setTitle("Home Utente");

        } catch (CredentialErrorException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handlerloginagenzia() {
        String userAgenzia = usernameAgenzia.getText();
        String passAgenzia = passwordAgenzia.getText();

        AgencyBean agency = new AgencyBean();
        agency.setUsername(userAgenzia);
        agency.setPassword(passAgenzia);

        RegLoginControllerApp login = new RegLoginControllerApp(agency);

        try {
            login.loginAgenzia();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/agenzia/agency_home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AgencyHomeController controller = loader.getController();
            controller.setUser(agency);
            controller.setStage(stage);
            controller.setButtonText();
            stage.setScene(scene);
            stage.setTitle("Home Agenzia");
        } catch (CredentialErrorException | SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText("Username o password errati");
            alert.showAndWait();
        }
    }
}
