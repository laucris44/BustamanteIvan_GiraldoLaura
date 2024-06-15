package com.clinicaOdontologica.exeptions;

public class BadRequestException extends Exception{
    public BadRequestException (String message) {
        //El super para que podamos usar en otras clases
        //NO return
        super(message);
    }
}
