package com.oocl.cultivation;

public class ParkingLot {
    private int capacity;

    public ParkingLot(int capacity) {

        this.capacity = capacity;
    }
    public ParkingLot(){
        this.capacity = 1;
    }

    public Ticket park(Car car) {
        return new Ticket();
    }
}
