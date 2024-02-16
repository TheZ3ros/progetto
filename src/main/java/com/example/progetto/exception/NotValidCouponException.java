package com.example.progetto.exception;

public class NotValidCouponException extends Exception{
    public  NotValidCouponException(String message){
        super(message);
    }
}