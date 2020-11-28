package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class ParkingBoyTest {
    @Test
    public void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() throws NotEnoughSpaceException{
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
    public void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking() throws NotEnoughSpaceException{
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
        assertEquals("Not enough space", notEnoughSpaceException.getMessage());
    }
    @Test
    public void should_return_car_when_fetch_car_given_parking_lot_that_parked_the_car() throws NotEnoughSpaceException,UnrecognizedParkingTicketException{
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
    public void should_return_null_when_fetched_car_given_used_ticket() throws NotEnoughSpaceException,UnrecognizedParkingTicketException{
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetchCar(ticket);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, ()->{parkingLot.fetchCar(ticket);});
        //then
        assertEquals("Unrecognized parking ticket",unrecognizedParkingTicketException.getMessage());
    }
    @Test
    public void should_return_null_when_fetched_car_given_wrong_ticket() throws NotEnoughSpaceException,UnrecognizedParkingTicketException{
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);
        //when
        Car expectedCar = car1;

        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, ()->{parkingLot.checkWrongTicket(ticket2,expectedCar);});

        //then
        assertEquals("Unrecognized parking ticket",unrecognizedParkingTicketException.getMessage());
    }


    


}
