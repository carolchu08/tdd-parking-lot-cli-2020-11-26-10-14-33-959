package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingBoy {
    List<ParkingBoy> parkingManagerList;

    public ParkingManager(List<ParkingLot> parkingLot) {
        super(parkingLot);
    }

    public ParkingManager() {
        super();
        parkingManagerList = new ArrayList<>();
    }

    public void addManagementList(ParkingBoy parkingBoy) {
        if (!parkingBoy.getClass().equals(ParkingManager.class)) {
            parkingManagerList.add(parkingBoy);
        }


    }

    public List<ParkingBoy> getParkingManagerList() {
        return parkingManagerList;
    }

    public Ticket orderParkingAction(Car car, ParkingBoy parkingBoy) throws NotEnoughSpaceException {
        if (parkingManagerList.contains(parkingBoy)) {
            return parkingBoy.park(car);
        }
        return null;
    }

    public Car orderFetchCar(Ticket ticket, ParkingBoy parkingBoy) throws UnrecognizedParkingTicketException {
        if(parkingManagerList.contains(parkingBoy)){
            return parkingBoy.fetchCar(ticket);
        }
        return null;
    }
}
