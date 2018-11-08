package car;

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

    public MyCar(String s)
    {
        s = s.trim();
        s = s.toLowerCase();
        Pattern pattern = Pattern.compile("(\\d+)(;|\t|\\,|\\s+)(\\d+)(;|\t|\\,|\\s+)(\\w+)");
        Matcher matcher = pattern.matcher(s);

        setDefaultTankCapacity();
        setDefaultFuelConsumption();
        setDefaultMaker();
        setDefaultMembers();

        if (!matcher.matches())
        {
            System.out.println("Wrong input, set default values");
            return;
        }

        int tankCapacity = Integer.parseInt(matcher.group(1));
        if (tankCapacity >= 20 && tankCapacity <= 80)
        {
            tankCapacity_ = tankCapacity;
        }

        int fuelConsumption = Integer.parseInt(matcher.group(3));
        if (fuelConsumption >= 3 && fuelConsumption <= 20)
        {
            fuelConsumption_ = fuelConsumption;
        }

        maker_ = CarMakers.convertString(matcher.group(5));
    }

    public void tankIt(double howMuch)
    {
        if (howMuch + fuel_ > tankCapacity_)
        {
            fuel_ = tankCapacity_;
            return;
        }

        if (howMuch < 0)
        {
            throw new IllegalArgumentException("Error fuel can not be < 0 ");
            //System.out.println("Error fuel can not be < 0");
            //return;
        }

        fuel_ = fuel_ + howMuch;
    }

    public void startTrip(double tripDistance)
    {
        if ((fuelConsumption_ * tripDistance / 100) > fuel_)
        {
            System.out.println("Not enough fuel in the tank!!!");
            return;
        }

        if (tripDistance < 0)
        {
            throw new IllegalArgumentException("Error fuel can not be < 0 ");
            //System.out.println("Error tripDistance can not be < 0");
            //return;
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
}
