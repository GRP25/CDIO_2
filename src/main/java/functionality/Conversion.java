package functionality;

public class Conversion {
    public static String nameConversion(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static String cprConversion(long cpr) {
        return cpr > 999999999 ? String.valueOf(cpr) : "0" + cpr ;
    }
}
