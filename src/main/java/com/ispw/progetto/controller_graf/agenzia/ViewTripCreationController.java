package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.CreateTripController;
import com.ispw.progetto.exception.DateNotValidException;
import com.ispw.progetto.exception.EmptystatementException;
import com.ispw.progetto.exception.SQLStatementException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewTripCreationController {

    private String imagePath;
    private byte[] imageBytes;
    private AgencyBean currentUser;
    private Stage stage;  // <-- campo Stage

    @FXML
    private Button agency;

    @FXML
    private ImageView imageViewer;

    @FXML
    private TextField nomecitta;

    @FXML
    private TextField disponibili;

    @FXML
    private DatePicker dataAnd;

    @FXML
    private DatePicker dataRit;

    @FXML
    private TextField prezzo;

    // Setter per lo stage da chiamare da AgencyHomeController
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(AgencyBean utente){
        this.currentUser = utente;
    }

    public void setButtonText() {
        agency.setText(currentUser.getUsername());
    }

    @FXML
    public void vaiAHome() {
        // Per tornare alla home, usa lo stage già settato
        try {
            FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/agenzia/agency_home.fxml"));
            Parent root = loader.load();
            AgencyHomeController controller = loader.getController();
            controller.setUser(currentUser);
            controller.setButtonText();
            controller.setStage(stage);  // importante passare lo stage al controller nuovo
            stage.setScene(new Scene(root));
            stage.setTitle("Home Agenzia");
        } catch (IOException e) {
            showErrorAlert("Errore caricamento schermata Home Agenzia");
        }
    }

    @FXML
    private void vaiAAgencyHome() {
        // In pratica puoi anche rimandare a vaiAHome()
        vaiAHome();
    }

    @FXML
    private void insertImage() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleziona l'immagine");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("File immagine", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Window window = agency.getScene().getWindow();
        File selectedFile = chooser.showOpenDialog(window);
        if (selectedFile != null) {
            try {
                imagePath = selectedFile.getAbsolutePath();
                Image image = new Image(selectedFile.toURI().toString());
                imageBytes = imageToBytes(image);
                imageViewer.setImage(image);
            } catch (IOException e) {
                showErrorAlert("Errore nel caricamento dell'immagine");
            }
        }
    }

    public byte[] imageToBytes(Image image) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        return outputStream.toByteArray();
    }

    @FXML
    private void submit() {
        try {
            String city = nomecitta.getText().trim();
            if (city.isEmpty() || imageBytes == null) {
                throw new EmptystatementException("Riempire tutti i campi");
            }

            int available = Integer.parseInt(disponibili.getText());

            LocalDate andataDate = dataAnd.getValue();
            LocalDate ritornoDate = dataRit.getValue();

            if (andataDate == null || ritornoDate == null) {
                throw new EmptystatementException("Selezionare entrambe le date");
            }
            if (andataDate.isBefore(LocalDate.now())) {
                dataAnd.setValue(null);
                throw new DateNotValidException("Valore di andata non valido");
            }
            if (ritornoDate.isBefore(andataDate)) {
                dataRit.setValue(null);
                throw new DateNotValidException("Valore di ritorno non valido");
            }

            Date andata = Date.valueOf(andataDate);
            Date ritorno = Date.valueOf(ritornoDate);
            float price = Float.parseFloat(prezzo.getText());

            TripBean trip = new TripBean(city, available, andata, ritorno, price, imageBytes);
            CreateTripController creation = new CreateTripController(trip);
            creation.uploadTrip();

            showInfoAlert("Viaggio creato con successo");

        } catch (NumberFormatException e) {
            showErrorAlert("Inserire un numero valido per i posti disponibili e il prezzo");
        } catch (DateNotValidException | EmptystatementException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException | IOException e) {
            showErrorAlert("Errore nel salvataggio del viaggio");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informazione");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
