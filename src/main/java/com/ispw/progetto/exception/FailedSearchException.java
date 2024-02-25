package com.ispw.progetto.exception;

import javafx.scene.control.TextField;

public class FailedSearchException extends Exception{

    public  FailedSearchException(String message){
        super(message);
    }

}