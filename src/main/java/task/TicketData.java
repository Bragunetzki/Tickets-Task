package task;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Record representing the ticket data.
 * <p>
 * This record encapsulates the details of a ticket, including origin and destination information,
 * departure and arrival times, carrier, number of stops, and price. It uses Gson annotations to map
 * JSON properties to record components.
 * </p>
 *
 * @param origin the origin code of the ticket
 * @param originName the name of the origin
 * @param destination the destination code of the ticket
 * @param destinationName the name of the destination
 * @param departureDate the departure date
 * @param departureTime the departure time
 * @param arrivalDate the arrival date
 * @param arrivalTime the arrival time
 * @param carrier the carrier name
 * @param stops the number of stops
 * @param price the price of the ticket
 */
public record TicketData(String origin,
                         @SerializedName("origin_name") String originName,
                         String destination,
                         @SerializedName("destination_name") String destinationName,
                         @SerializedName("departure_date") LocalDate departureDate,
                         @SerializedName("departure_time") LocalTime departureTime,
                         @SerializedName("arrival_date") LocalDate arrivalDate,
                         @SerializedName("arrival_time") LocalTime arrivalTime,
                         String carrier,
                         int stops,
                         double price) {
}
