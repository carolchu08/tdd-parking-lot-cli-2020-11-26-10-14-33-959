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
            Car fetchedCar = ticketCarMap.get(ticket);
            ticketCarMap.remove(ticket, ticketCarMap.get(ticket));
            return fetchedCar;
        }
        return null;

    }


    public Car checkWrongTicket(Ticket ticket, Car expectedCar){

        if(expectedCar.equals(ticketCarMap.get(ticket))){
            return expectedCar;
        }
        return null;

    }

}
