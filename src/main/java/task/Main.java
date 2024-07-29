package task;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * The main class of the application that analyzes flight ticket data.
 * <p>
 * This class reads ticket data from a JSON file, calculates minimum flight durations, as well as
 * the difference between average and median ticket prices for flights between specified cities,
 * and prints the results to the console.
 * </p>
 */
public class Main {
    /**
     * The entry point of the application.
     * <p>
     * This method reads ticket data from a JSON file, processes it to calculate flight durations
     * and price differences, and outputs the results to the console.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<TicketData> tickets = TicketJsonReader.readTickets("tickets.json");
        if (tickets == null) {
            System.out.println("No tickets could be read.");
        } else {
            String city1 = "Владивосток";
            String city2 = "Тель-Авив";

            Map<String, Duration> flightDurations = TicketDataAnalyzer.calculateMinFlightsTimes(tickets, city1, city2);
            double difference = TicketDataAnalyzer.calculateAverageAndMedianPriceDifference(tickets, city1, city2);

            printFlightDurationResult(flightDurations, city1, city2);
            printDifferenceResult(difference, city1, city2);
        }
    }

    /**
     * Prints the minimum flight durations between two cities for each carrier.
     *
     * @param flightDurations a map of carrier names to their minimum flight durations
     * @param city1 the name of the origin city
     * @param city2 the name of the destination city
     */
    private static void printFlightDurationResult(Map<String, Duration> flightDurations, String city1, String city2) {
        System.out.println("Минимальное время полета между городами " + city1 + " и " + city2 + " для каждого авиаперевозчика: ");
        for (Map.Entry<String, Duration> entry : flightDurations.entrySet()) {
            System.out.println(entry.getKey() + " - " + formatDuration(entry.getValue()));
        }
    }

    /**
     * Prints the difference between the average and median prices for flights between two cities.
     *
     * @param difference the calculated difference between average and median ticket prices
     * @param city1 the name of the origin city
     * @param city2 the name of the destination city
     */
    private static void printDifferenceResult(double difference, String city1, String city2) {
        System.out.println("Разница между средней ценой и медианой для полета между городами " + city1 + " и " + city2 + ": " + difference);
    }

    /**
     * Formats a {@link Duration} object into an easily readable string in Russian.
     *
     * @param duration the {@link Duration} that will be formatted
     * @return a string representing the formatted {@link Duration}
     */
    private static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days).append(" д. ");
        }
        if (hours > 0) {
            sb.append(hours).append(" ч. ");
        }
        if (minutes > 0) {
            sb.append(minutes).append(" м.");
        }

        return sb.toString().trim();
    }
}