package com.oocl.cultivation;

public class NotEnoughSpaceException extends Exception{


    public NotEnoughSpaceException(String errorMessage) {
        super(errorMessage);
    }

}
