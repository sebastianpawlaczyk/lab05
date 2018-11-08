package car;

public enum CarMakers
{
    FIAT,
    FORD,
    VOLVO,
    BMW,
    CHRYSLER,
    GM,
    OTHER;

    public static CarMakers convertString(String s)
    {
        s = s.trim();
        s = s.toLowerCase();

        switch (s)
        {
            case "fiat" : return FIAT;
            case "ford" : return FORD;
            case "volvo" : return VOLVO;
            case "bmw" : return BMW;
            case "chrysler" : return CHRYSLER;
            case "gm" : return GM;
            default : return OTHER;
        }
    }

    public static String getString(CarMakers carMakers)
    {
        switch (carMakers)
        {
            case FIAT : return "FIAT";
            case FORD : return  "Ford";
            case VOLVO : return "Volvo";
            case BMW : return "BMW";
            case CHRYSLER : return "Chrysler";
            case GM : return "GM";
            default : return "Other";
        }
    }
}
