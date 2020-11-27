package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class ParkingBoyTest {
    @Test
    public void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        //when
        parkingBoy.park(car);

        //then
        verify(parkingLot,times(1)).park(car);

    }
    @Test
    public void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();


        //when
        Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);

    }
    @Test
    public void should_be_parked_when_park_multiple_cars_given_multiple_car_and_parking_lot_only_1_space() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
                
        //when
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);
        
        
        //then
        assertNotNull(ticket1);
        assertNull(ticket2);
        
    }
    


}
