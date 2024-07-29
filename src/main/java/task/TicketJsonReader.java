package task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Utility class for reading ticket data from JSON file.
 */
public class TicketJsonReader {
    /**
     * Reads a list of tickets from the specified JSON file.
     *
     * @param filepath the path to the JSON file
     * @return a list of {@link TicketData} objects, or {@code null} if an error occurs or if the file does not contain any tickets
     */
    public static List<TicketData> readTickets(String filepath) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        builder.registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer());
        Gson gson = builder.create();

        TicketDataWrapper ticketDataWrapper = null;

        try (FileReader reader = new FileReader(filepath, StandardCharsets.UTF_8)) {
            ticketDataWrapper = gson.fromJson(reader, TicketDataWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ticketDataWrapper == null) {
            return null;
        }
        if (ticketDataWrapper.tickets() == null) {
            return null;
        }
        return ticketDataWrapper.tickets();
    }
}
