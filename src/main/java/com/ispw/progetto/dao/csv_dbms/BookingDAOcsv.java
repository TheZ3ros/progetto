package com.ispw.progetto.dao.csv_dbms;

import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.exception.CSVInteractionException;
import com.ispw.progetto.model.UserTrip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class BookingDAOcsv implements BookingDAO {

    private static final String CSV_FILE_NAME = "file/booking.csv";
    private final File fd;

    public BookingDAOcsv(){
        this.fd=new File(CSV_FILE_NAME);
    }

    @Override
    public void setTripBook(UserTrip book) throws AlreadyPrenotedException, IOException {
        int idTrip = book.getIdTrip();
        String username = book.getUsername();
        String line = idTrip + "," + username;
        try{
            alreadyExist(book);
        }
        catch(AlreadyPrenotedException | CSVInteractionException e){
            throw new AlreadyPrenotedException(e.getMessage());
        }

        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd,true)))) {
            csvWriter.writeNext(new String[]{line});

        } catch (IOException e) {
            throw new IOException("Errore durante la scrittura del file: " + e.getMessage());
        }
    }

    @Override
    public void alreadyExist(UserTrip booking) throws AlreadyPrenotedException, IOException, CSVInteractionException {
        String usernameToFind=booking.getUsername();
        int idToFind=booking.getIdTrip();

        try (CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){

            String[] nextRecord;
            String check = idToFind + "," + usernameToFind;

            while ((nextRecord = csvReader.readNext()) != null) {
                String recordAsString = String.join(",", nextRecord); // Concatena gli elementi dell'array con ","

                if (recordAsString.equals(check)) {
                    throw new AlreadyPrenotedException("Già prenotato");
                }
            }

        } catch (IOException e) {
            throw new IOException("errore lettura file");
        } catch (CsvValidationException e) {
            throw new CSVInteractionException("Errore nell'interazione con il CSV");
        }
    }
}
