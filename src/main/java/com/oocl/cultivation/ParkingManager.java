package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingBoy{
    List <ParkingBoy> parkingManagerList;
    public ParkingManager(List<ParkingLot> parkingLot) {
        super(parkingLot);
    }

    public ParkingManager() {
        super();
        parkingManagerList = new ArrayList<>();
    }

    public void addManagementList(ParkingBoy parkingBoy) {
           parkingManagerList.add(parkingBoy);


    }

    public List<ParkingBoy> getParkingManagerList() {
        return parkingManagerList;
    }
}
