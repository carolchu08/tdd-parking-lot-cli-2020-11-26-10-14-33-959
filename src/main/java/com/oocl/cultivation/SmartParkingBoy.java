package com.oocl.cultivation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket park(Car car) throws NotEnoughSpaceException {
        ParkingLot parkingLotToBeUsed = Collections.max(this.getParkingLot(), Comparator.comparing(c -> c.getRemainingSpace()));

        if (parkingLotToBeUsed.isHasSpaceForParking()) {
            return parkingLotToBeUsed.park(car);
        }

        throw new NotEnoughSpaceException("Not Enough Space");
    }
}