package com.oocl.cultivation;

public class UnrecognizedParkingTicketException extends Exception {

    public UnrecognizedParkingTicketException(String errorMessage) {
        super(errorMessage);
    }
}
