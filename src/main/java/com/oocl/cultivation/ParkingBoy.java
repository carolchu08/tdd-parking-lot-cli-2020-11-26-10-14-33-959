package com.oocl.cultivation;


import java.util.List;

public class ParkingBoy {
    private  List <ParkingLot>  parkingLot;

    public ParkingBoy(List <ParkingLot> parkingLot) {

        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException{
        for(ParkingLot eachParkingLot : parkingLot) {
            if(eachParkingLot.isHasSpaceForParking()) {
                return eachParkingLot.park(car);
            }
        }

       throw  new NotEnoughSpaceException("Not enough space");
    }


    public Car fetchCar(Ticket ticket1) throws UnrecognizedParkingTicketException {
        for(ParkingLot eachParkingLot : parkingLot) {
                return eachParkingLot.fetchCar(ticket1);
            }
                return null;
        }


    public Car checkWrongTicket(Ticket ticket, Car expectedCar) throws UnrecognizedParkingTicketException {
        for(ParkingLot eachParkingLot : parkingLot) {
            return eachParkingLot.checkWrongTicket(ticket, expectedCar);
        }
        return null;
    }
}
