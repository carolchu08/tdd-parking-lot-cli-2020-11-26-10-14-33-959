package com.oocl.cultivation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket park(Car car) throws NotEnoughSpaceException {
        ParkingLot parkingLotToBeUsed = Collections.max(this.getParkingLot(), Comparator.comparing(c -> c.getRemainingSpace()/c.getCapacity()));

        if (parkingLotToBeUsed.isHasSpaceForParking()) {
            return parkingLotToBeUsed.park(car);
        }

        throw new NotEnoughSpaceException("Not Enough Space");
    }
}
