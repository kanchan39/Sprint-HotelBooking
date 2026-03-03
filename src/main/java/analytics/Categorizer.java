package analytics;

public class Categorizer {

    public static String categorizeStay(long nights) {

        if (nights <= 2) return "Short Stay";
        if (nights <= 5) return "Medium Stay";
        return "Long Stay";
    }
}