package task;

import java.util.List;

/**
 * Wrapper record for a list of {@link TicketData} objects.
 * <p>
 * This record encapsulates a collection of ticket data, allowing for easier deserialization
 * of JSON data that contains a list of tickets.
 * </p>
 *
 * @param tickets the list of {@link TicketData} objects
 */
public record TicketDataWrapper(List<TicketData> tickets) {
}
