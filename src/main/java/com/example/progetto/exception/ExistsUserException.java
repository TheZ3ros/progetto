package com.example.progetto.exception;

public class ExistsUserException extends Exception{
    public  ExistsUserException(String message){
        super(message);
    }
}