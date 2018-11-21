package car;

import exception.FuelException;
import exception.TripException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyCarTests
{
    @Test
    public void createDefaultCarAndSetDefaultValues()
    {
        MyCar myCar = new MyCar();

        // = 0
        Assertions.assertEquals(myCar.getMilage(), 0);
        Assertions.assertEquals(myCar.getLastTripDistance(), 0);
        Assertions.assertEquals(myCar.getLastTripDistance(), 0);

        // default values -> maker = OTHER, tankCapacity = 40, fuelConsumption = 8
        Assertions.assertEquals(myCar.toString(), "Other tankCapacity 40.0 fuelConsumption 8.0");
    }

    @Test
    public void createCarWithStringValue() throws Exception
    {
        MyCar myCar = new MyCar("50;5   bmw");
        //0
        Assertions.assertEquals(myCar.getMilage(), 0);
        Assertions.assertEquals(myCar.getLastTripDistance(), 0);
        Assertions.assertEquals(myCar.getLastTripDistance(), 0);

        // default values -> maker = OTHER, tankCapacity = 40, fuelConsumption = 8
        Assertions.assertEquals(myCar.toString(), "BMW tankCapacity 50.0 fuelConsumption 5.0");
    }

    @Test
    public void createCarWithWrongStringAndThrowException()
    {
        boolean isThrowException = false;
        try {
            MyCar myCar = new MyCar("fsaf;5  3 fasfa");
        }
        catch (Exception e)
        {
            isThrowException = true;
        }

        Assertions.assertTrue(isThrowException);
    }

    @Test
    public void tryToTankToMuchFuelAndSetFuelLevelToTankCapacity() throws Exception
    {
        MyCar myCar = new MyCar("50;5   bmw");
        myCar.tankIt(100);

        Assertions.assertEquals(myCar.getFuelLevel(), 50);
    }

    @Test
    public void tryToTankFuelSmallerThanZeroAndThrowException() throws Exception
    {
        MyCar myCar = new MyCar("50;5   bmw");

        Assertions.assertThrows(FuelException.class,
                ()->{
                    myCar.tankIt(-33);
                });
    }

    @Test
    public void tankProperValue() throws Exception
    {
        MyCar myCar = new MyCar("50;5   bmw");
        myCar.tankIt(34);

        Assertions.assertEquals(myCar.getFuelLevel(), 34);
    }

    @Test
    public void startTripWithEnoughFuelAndUpdateValues() throws Exception
    {
        MyCar myCar = new MyCar("50;5   bmw");
        myCar.tankIt(50);
        myCar.startTrip(100);
        myCar.startTrip(100);

        Assertions.assertEquals(myCar.getFuelLevel(), 40);
        Assertions.assertEquals(myCar.getLastTripDistance(), 100);
        Assertions.assertEquals(myCar.getMilage(), 200);
    }

    @Test
    public void startTripWithoutEnoughFuelAndThrowException() throws Exception
    {
        MyCar myCar = new MyCar("50;5   bmw");
        myCar.tankIt(2);

        Assertions.assertThrows(TripException.class,
                ()->{
                    myCar.startTrip(100);
                });
    }

    @Test
    public void startTripWithSmallerThanZeroAndThrowException() throws Exception
    {
        MyCar myCar = new MyCar("50;5   bmw");

        Assertions.assertThrows(TripException.class,
                ()->{
                    myCar.startTrip(-33);
                });

    }
}