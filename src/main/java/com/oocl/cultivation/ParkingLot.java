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
        ticketCarMap = new HashMap<>();
    }

    public Ticket park(Car car) throws NotEnoughSpaceException{
        if(capacity-ticketCarMap.size()<=0){
           throw new NotEnoughSpaceException("Not enough space");
        }
        Ticket ticket = new Ticket();
        this.ticketCarMap.put(ticket,car);
        return ticket;
    }
    public Car fetchCar (Ticket ticket){
        if(ticketCarMap.containsKey(ticket)){
            return ticketCarMap.get(ticket);
        }
        return null;

    }

    public boolean isTicketValid(Ticket ticket) {
        if(ticketCarMap.containsKey(ticket)){
            return false;
        }
        return true;
    }

    public Car fetchedCar(boolean isTicketValid, Ticket ticket) {
        if(isTicketValid){
            return ticketCarMap.get(ticket);
        }
        return null;
    }
}
