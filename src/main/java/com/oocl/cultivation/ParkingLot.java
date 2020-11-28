package com.oocl.cultivation;

import java.util.HashMap;
import java.util.List;
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

    public Ticket park(Car car) {
           Ticket ticket = new Ticket();
           this.ticketCarMap.put(ticket, car);
           return ticket;
    }
    public Boolean isHasSpaceForParking()  {
            if(capacity-ticketCarMap.size()>0){
              return true;
            }
            return false;

    }
    public Car fetchCar (Ticket ticket) throws UnrecognizedParkingTicketException{
        if(ticketCarMap.containsKey(ticket)){
            Car fetchedCar = ticketCarMap.get(ticket);
            ticketCarMap.remove(ticket, ticketCarMap.get(ticket));
            return fetchedCar;
        }
        throw new UnrecognizedParkingTicketException("Unrecognized parking ticket");

    }


    public Car checkWrongTicket(Ticket ticket, Car expectedCar) throws UnrecognizedParkingTicketException{

        if(expectedCar.equals(ticketCarMap.get(ticket))){
            return expectedCar;
        }
        throw new UnrecognizedParkingTicketException("Unrecognized parking ticket");

    }

    public int getRemainingSpace(){

        return this.capacity - ticketCarMap.size();

    }

    public int getCapacity() {
        return capacity;
    }
}
