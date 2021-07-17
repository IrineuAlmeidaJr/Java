public class MinutesToYearsDaysCalculator {

    public static final String INVALID_VALUE_MESSAGE = "Invalid Value";

    public static void printYearsAndDays(long minutes) {

        if(minutes < 0) {
            System.out.println(INVALID_VALUE_MESSAGE);
        }
        else {
            long hour = minutes / 60;
            long day = hour / 24;
            long year = day / 365;

            System.out.println(minutes + " min = " + year + " y and "
                    + day % 365 + " d");
        }
    }
}
