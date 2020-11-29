package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class ParkingBoyTest {
    //    @Test
//    public void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() throws NotEnoughSpaceException {
//        //given
//        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
//        List<ParkingLot> parkingLotList = new ArrayList<>();
//        parkingLotList.add(parkingLot);
//
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
//        Car car = new Car();
//
//        //when
//        parkingBoy.park(car);
//
//        //then
//        verify(parkingLot, times(1)).park(car);
//    }
    @Test
    public void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();


        //when
        Ticket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);

    }

    @Test
    public void should_display_not_enough_space_msg_when_park_multiple_cars_given_multiple_car_and_parking_lot_only_1_space() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        parkingBoy.park(car1);
        NotEnoughSpaceException notEnoughSpaceException = assertThrows(NotEnoughSpaceException.class, () -> {
            parkingBoy.park(car2);
        });


        //then
        assertEquals("Not enough space", notEnoughSpaceException.getMessage());
    }

    @Test
    public void should_return_car_when_fetch_car_given_parking_lot_that_parked_the_car() throws NotEnoughSpaceException, UnrecognizedParkingTicketException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();


        //when
        Ticket ticket1 = parkingBoy.park(car);
        Car resultCar = parkingBoy.fetchCar(ticket1);


        //then
        assertEquals(car, resultCar);
    }

    @Test
    public void should_return_null_when_fetched_car_given_used_ticket() throws NotEnoughSpaceException, UnrecognizedParkingTicketException {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = parkingBoy.park(car);
        Car resultCar = parkingBoy.fetchCar(ticket);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetchCar(ticket);
        });
        //then
        assertEquals("Unrecognized parking ticket", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    public void should_return_null_when_fetched_car_given_wrong_ticket() throws NotEnoughSpaceException, UnrecognizedParkingTicketException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        //when
        Car expectedCar = car1;

        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.checkWrongTicket(ticket2, expectedCar);
        });

        //then
        assertEquals("Unrecognized parking ticket", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    public void should_return_tickets_when_park_car_given_firstparking_has_1_space_secondparking_has_3_space_with_2_cars() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(3));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        //when
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);

        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);


    }

    @Test
    public void should_return_tickets_when_park_car_given_firstparking_has_no_space_secondparking_has_3_space_with_1_cars() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(0));
        parkingLotList.add(new ParkingLot(3));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);

    }

    @Test
    public void should_park_cars_to_parking_lot_with_more_capacity_when_park_given_two_parking_lot() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1= new ParkingLot(3);
        ParkingLot parkingLot2= new ParkingLot(5);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);


        //when
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();


        Ticket ticket1 = smartParkingBoy.park(car1);
        Ticket ticket2 = smartParkingBoy.park(car2);
        Ticket ticket3 = smartParkingBoy.park(car3);


        //then
        assertEquals(2,parkingLot1.getRemainingSpace());
        assertEquals(3,parkingLot2.getRemainingSpace());

    }
    @Test
    public void should_park_car_to_parkinglot_with_higher_rate_when_park_given_two_parkinglot() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1= new ParkingLot(3);
        ParkingLot parkingLot2= new ParkingLot(5);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        //when
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();


        Ticket ticket1 = superSmartParkingBoy.park(car1);
        Ticket ticket2 = superSmartParkingBoy.park(car2);
        Ticket ticket3 = superSmartParkingBoy.park(car3);
        
        //then
        assertEquals(1,parkingLot1.getRemainingSpace());
        assertEquals(4,parkingLot2.getRemainingSpace());
        
    }
    @Test
    public void should_successfully_add_parkingboy_to_management_list_when_add_parkingboy_given_a_manager() {
        //given
        ParkingBoy parkingBoy1 = new ParkingBoy();
        ParkingBoy parkingBoy2 =new ParkingBoy();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingManager parkingManager = new ParkingManager();
        List <ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy1);
        parkingBoyList.add(parkingBoy2);
        //when
        for (ParkingBoy parkingBoy : parkingBoyList) {
            parkingManager.addManagementList(parkingBoy);
        }
        parkingManager.addManagementList(superSmartParkingBoy);
        parkingManager.addManagementList(smartParkingBoy);


        //then
        assertEquals(parkingBoy1,parkingManager.getParkingManagerList().get(0));
        assertEquals(parkingBoy2,parkingManager.getParkingManagerList().get(1));
        assertEquals(superSmartParkingBoy,parkingManager.getParkingManagerList().get(2));
        assertEquals(smartParkingBoy,parkingManager.getParkingManagerList().get(3));

    }
    @Test
    public void should_return_size_is_0_for_parkingmangerList_when_add_parkingmanger_to_management_list_when_add_other_parkingmanager_given_a_manager() {
        //given
        ParkingManager parkingManager1 = new ParkingManager();
        ParkingManager parkingManager2 = new ParkingManager();

        //when
        parkingManager1.addManagementList(parkingManager2);

        //then
        assertEquals(0,parkingManager1.getParkingManagerList().size());
    }


    @Test
    public void should_unable_to_order_parkingboys_for_parking_when_order_park_given_the_parking_boys_not_in_the_list() throws NotEnoughSpaceException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ParkingLot parkingLot3 = new ParkingLot(3);
        List <ParkingLot> parkingLotList1 = new ArrayList<>();
        List <ParkingLot> parkingLotList2 = new ArrayList<>();
        List <ParkingLot> parkingLotList3 = new ArrayList<>();
        parkingLotList1.add(parkingLot1);
        parkingLotList2.add(parkingLot2);
        parkingLotList3.add(parkingLot3);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList2);
        SmartParkingBoy smartParkingBoy1 = new SmartParkingBoy(parkingLotList3);
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.addManagementList(superSmartParkingBoy);
        parkingManager.addManagementList(smartParkingBoy);
        //when
        Ticket ticket1 = parkingManager.orderParkingAction(car1,smartParkingBoy);
        Ticket ticket2 = parkingManager.orderParkingAction(car2,superSmartParkingBoy);
        Ticket ticket3 = parkingManager.orderParkingAction(car3,smartParkingBoy1);

        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertNull(ticket3);

    }
    @Test
    public void should_return_not_enough_space_msg_when_orderparkingaction_given_the_parkinglots_managed_by_the_parkingboy_does_not_have_enough_space() throws NotEnoughSpaceException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List <ParkingLot> parkingLotList1 = new ArrayList<>();
        List <ParkingLot> parkingLotList2 = new ArrayList<>();
        parkingLotList1.add(parkingLot1);
        parkingLotList2.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList2);
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.addManagementList(superSmartParkingBoy);
        parkingManager.addManagementList(smartParkingBoy);
        //when
        Ticket ticket1 = parkingManager.orderParkingAction(car1,smartParkingBoy);
        Ticket ticket2 = parkingManager.orderParkingAction(car2,superSmartParkingBoy);
        NotEnoughSpaceException notEnoughSpaceException = assertThrows(NotEnoughSpaceException.class, () -> {
            parkingManager.orderParkingAction(car3,superSmartParkingBoy);
        });


        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertEquals("Not Enough Space",notEnoughSpaceException.getMessage());
    }
    




}
