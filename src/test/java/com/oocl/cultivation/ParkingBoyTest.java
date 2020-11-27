package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
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
    public void should_display_not_enough_space_msg_when_park_multiple_cars_given_multiple_car_and_parking_lot_only_1_space() throws NotEnoughSpaceException{
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
                
        //when
        parkingLot.park(car1);
        NotEnoughSpaceException notEnoughSpaceException = assertThrows(NotEnoughSpaceException.class, ()->{parkingLot.park(car2);});

        
        
        //then
        assertEquals("Not enough space", notEnoughSpaceException);
    }
    @Test
    public void should_return_car_when_fetch_car_given_parking_lot_that_parked_the_car() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(1);
                
        //when
        Ticket ticket1 = parkingLot.park(car);
        Car resultCar = parkingLot.fetchCar(ticket1);

        
        
        //then
        assertEquals(car,resultCar);
    }
    @Test
    public void should_return_false_when_fetched_car_given_used_ticket() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(car);
        //when
        boolean isTicketValid = parkingLot.isTicketValid(ticket);
        
        //then
        assertEquals(false, isTicketValid);
    }

    


}
