package car;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CarMakersTests
{
    @Test
    public void convertStringToProperEnumValue()
    {
        Assertions.assertEquals(CarMakers.convertString("fiat"), CarMakers.FIAT);
        Assertions.assertEquals(CarMakers.convertString("FORD"), CarMakers.FORD);
        Assertions.assertEquals(CarMakers.convertString("VolVO"), CarMakers.VOLVO);
        Assertions.assertEquals(CarMakers.convertString("bmW"), CarMakers.BMW);
        Assertions.assertEquals(CarMakers.convertString("chrysler"), CarMakers.CHRYSLER);
        Assertions.assertEquals(CarMakers.convertString("GM"), CarMakers.GM);
        Assertions.assertEquals(CarMakers.convertString("gasgasgaga"), CarMakers.OTHER);
    }

    @Test
    public void convertEnumToProperString()
    {
        Assertions.assertEquals(CarMakers.getString(CarMakers.FIAT), "FIAT");
        Assertions.assertEquals(CarMakers.getString(CarMakers.FORD), "Ford");
        Assertions.assertEquals(CarMakers.getString(CarMakers.VOLVO), "Volvo");
        Assertions.assertEquals(CarMakers.getString(CarMakers.BMW), "BMW");
        Assertions.assertEquals(CarMakers.getString(CarMakers.CHRYSLER), "Chrysler");
        Assertions.assertEquals(CarMakers.getString(CarMakers.GM), "GM");
        Assertions.assertEquals(CarMakers.getString(CarMakers.OTHER), "Other");
    }
}