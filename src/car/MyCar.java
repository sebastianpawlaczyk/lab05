package car;

import exception.FuelException;
import exception.MyCarWrongFormatException;
import exception.TankCapacityException;
import exception.TripException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyCar
{
    private double tankCapacity_;
    private double fuelConsumption_;
    private CarMakers maker_;
    private double milage_;
    private double fuel_;
    private double lastDistance_;

    private void setDefaultTankCapacity()
    {
        tankCapacity_ = 40;
    }

    private void setDefaultFuelConsumption()
    {
        fuelConsumption_ = 8;
    }

    private void setDefaultMaker()
    {
        maker_ = CarMakers.OTHER;
    }

    private void setDefaultMembers()
    {
        milage_ = 0;
        fuel_ = 0;
        lastDistance_ = 0;
    }

    public MyCar()
    {
        setDefaultTankCapacity();
        setDefaultFuelConsumption();
        setDefaultMaker();
        setDefaultMembers();

    }

    public MyCar(String s) throws Exception
    {
        s = s.trim();
        s = s.toLowerCase();
        s = s.replaceAll(",",".");
        Pattern pattern = Pattern.compile("(\\d+|\\d+\\.\\d+)(;|\t|\\,|\\s+)(\\d+|\\d+\\.\\d+)(;|\t|\\,|\\s+)(\\w+)");
        Matcher matcher = pattern.matcher(s);

        if (!matcher.matches())
        {
            throw new MyCarWrongFormatException("Format of argument in the constructor is incorrect");
        }

        double tankCapacity = Double.parseDouble(matcher.group(1));
        if (!(tankCapacity >= 20 && tankCapacity <= 80))
        {
            throw new TankCapacityException("Tank capacity is not in the range");
        }

        double fuelConsumption = Double.parseDouble(matcher.group(3));
        if (!(fuelConsumption >= 3 && fuelConsumption <= 20))
        {
            throw new FuelException("FuelConsumption is not in the range");
        }

        fuelConsumption_ = fuelConsumption;
        tankCapacity_ = tankCapacity;
        maker_ = CarMakers.convertString(matcher.group(5));
    }

    public void tankIt(double howMuch) throws Exception
    {
        if (howMuch + fuel_ > tankCapacity_)
        {
            fuel_ = tankCapacity_;
            return;
        }

        if (howMuch < 0)
        {
            throw new FuelException("fuel can not be < 0");
        }

        fuel_ = fuel_ + howMuch;
    }

    public void startTrip(double tripDistance) throws Exception
    {
        if ((fuelConsumption_ * tripDistance / 100) > fuel_)
        {
            throw new TripException("Not enough fuel in the tank to start trip");
        }

        if (tripDistance < 0)
        {
            throw new TripException("Trip distance can not be < 0");
        }

        milage_ = milage_ + tripDistance;
        lastDistance_ = tripDistance;
        fuel_ = fuel_  - (fuelConsumption_ * tripDistance / 100);

    }

    public double getMilage()
    {
        return milage_;
    }

    public double getLastTripDistance()
    {
        return lastDistance_;
    }

    public double getFuelLevel()
    {
        return fuel_;
    }

    public String toString()
    {
        return CarMakers.getString(maker_) + " tankCapacity " + String.valueOf(tankCapacity_)
                + " fuelConsumption " + String.valueOf(fuelConsumption_);
    }

    public static void main(String args[]) throws Exception
    {
        MyCar myCar = new MyCar("40,7 8,7 ford");
    }
}
