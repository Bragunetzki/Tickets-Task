package task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketJsonReaderTest {
    @TempDir
    Path tempDir;

    @Test
    void testReadTickets_validJsonFile() throws IOException {
        File tempFile = tempDir.resolve("tickets_valid.json").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("""
                    {"tickets": [{
                        "origin": "VVO",
                        "origin_name": "Владивосток",
                        "destination": "TLV",
                        "destination_name": "Тель-Авив",
                        "departure_date": "12.05.18",
                        "departure_time": "16:20",
                        "arrival_date": "12.05.18",
                        "arrival_time": "22:10",
                        "carrier": "TK",
                        "stops": 3,
                        "price": 12400
                      }]}""");
        }

        List<TicketData> tickets = TicketJsonReader.readTickets(tempFile.getPath());

        assertNotNull(tickets);
        assertEquals(1, tickets.size());
        TicketData ticket = tickets.get(0);
        assertEquals("VVO", ticket.origin());
        assertEquals("Владивосток", ticket.originName());
        assertEquals("TLV", ticket.destination());
        assertEquals("Тель-Авив", ticket.destinationName());
        assertEquals(LocalDate.of(2018, 5, 12), ticket.departureDate());
        assertEquals(LocalTime.of(16, 20), ticket.departureTime());
        assertEquals(LocalDate.of(2018, 5, 12), ticket.arrivalDate());
        assertEquals(LocalTime.of(22, 10), ticket.arrivalTime());
        assertEquals("TK", ticket.carrier());
        assertEquals(3, ticket.stops());
        assertEquals(12400, ticket.price());
    }

    @Test
    void testReadTickets_invalidJsonFile() throws IOException {
        File tempFile = tempDir.resolve("tickets_invalid.json").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("{ \"invalid\": \"data\" }");
        }
        List<TicketData> tickets = TicketJsonReader.readTickets(tempFile.getPath());
        assertNull(tickets);
    }

    @Test
    void testReadTickets_nonExistentFile() {
        List<TicketData> tickets = TicketJsonReader.readTickets("non_existent_file.json");
        assertNull(tickets);
    }

    @Test
    void testReadTickets_emptyJsonFile() throws IOException {
        File tempFile = tempDir.resolve("empty.json").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("{}");
        }
        List<TicketData> tickets = TicketJsonReader.readTickets(tempFile.getPath());
        assertNull(tickets);
    }
}