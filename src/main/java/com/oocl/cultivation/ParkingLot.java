package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    Map<Ticket,Car> ticketCarMap ;

    public ParkingLot(int capacity) {

        this.capacity = capacity;
        ticketCarMap = new HashMap<>();
    }
    public ParkingLot(){
        this.capacity = 1;
    }

    public Ticket park(Car car) {
        if(capacity<=0){
            return null;
        }
        capacity--;
        return new Ticket();
    }
    public Car fetchCar (Ticket ticket){

       return null;
    }
}
