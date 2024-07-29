package task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * Utility class for analyzing ticket data.
 * <p>
 * This class provides methods to calculate flight durations, average prices, median prices,
 * and the difference between average and median prices for flights based on origin and destination.
 * </p>
 */
public class TicketDataAnalyzer {
    /**
     * Calculates the minimum flight durations for each carrier between the provided origin and destination.
     *
     * @param tickets the list of ticket data
     * @param origin the origin name to filter tickets
     * @param destination the destination name to filter tickets
     * @return a map where the keys are carrier names and the values are the minimum flight durations
     */
    public static Map<String, Duration> calculateMinFlightsTimes(List<TicketData> tickets, String origin, String destination) {
        Map<String, Duration> carrierFlightDurations = new HashMap<>();

        for (TicketData ticket : tickets) {
            if (ticket.originName().equals(origin) && ticket.destinationName().equals(destination)) {
                LocalDateTime departureDateTime = LocalDateTime.of(ticket.departureDate(), ticket.departureTime());
                LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.arrivalDate(), ticket.arrivalTime());

                Duration duration = Duration.between(departureDateTime, arrivalDateTime);

                String carrier = ticket.carrier();
                Duration existingDuration = carrierFlightDurations.getOrDefault(carrier, ChronoUnit.FOREVER.getDuration());
                if (duration.compareTo(existingDuration) < 0) {
                    carrierFlightDurations.put(carrier, duration);
                }
            }
        }
        return carrierFlightDurations;
    }

    /**
     * Calculates the average price of tickets for the specified origin and destination.
     *
     * @param tickets the list of ticket data
     * @param origin the origin name to filter tickets
     * @param destination the destination name to filter tickets
     * @return the average price of tickets, or NaN if no tickets are found
     */
    public static double calculateAveragePrice(List<TicketData> tickets, String origin, String destination) {
        OptionalDouble average = tickets.stream()
                .filter(t -> t.originName().equals(origin) && t.destinationName().equals(destination))
                .mapToDouble(TicketData::price)
                .average();
        if (average.isPresent())
            return average.getAsDouble();
        else
            return Double.NaN;
    }

    /**
     * Calculates the median price of tickets for the specified origin and destination.
     *
     * @param tickets the list of ticket data
     * @param origin the origin name to filter tickets
     * @param destination the destination name to filter tickets
     * @return the median price of tickets, or NaN if no tickets are found
     */
    public static double calculateMedianPrice(List<TicketData> tickets, String origin, String destination) {
        List<Double> sortedPrices = tickets.stream()
                .filter(t -> t.originName().equals(origin) && t.destinationName().equals(destination))
                .map(TicketData::price)
                .sorted()
                .toList();

        int size = sortedPrices.size();
        if (size <= 0)
            return Double.NaN;
        if (size % 2 == 1) {
            return sortedPrices.get(size / 2);
        } else {
            double middle1 = sortedPrices.get(size / 2 - 1);
            double middle2 = sortedPrices.get(size / 2);
            return (middle1 + middle2) / 2.0;
        }
    }

    /**
     * Calculates the difference between the average and median prices for the specified origin and destination.
     *
     * @param tickets the list of ticket data
     * @param origin the origin name to filter tickets
     * @param destination the destination name to filter tickets
     * @return the difference between average and median prices
     */
    public static double calculateAverageAndMedianPriceDifference(List<TicketData> tickets, String origin, String destination) {
        double avg = calculateAveragePrice(tickets, origin, destination);
        double median = calculateMedianPrice(tickets, origin, destination);
        return avg - median;
    }
}
