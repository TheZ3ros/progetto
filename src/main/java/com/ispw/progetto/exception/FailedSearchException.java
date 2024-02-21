package com.ispw.progetto.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class FailedSearchException extends Exception{
    private TextField textField;

    public  FailedSearchException(String message){
        super(message);
    }

}