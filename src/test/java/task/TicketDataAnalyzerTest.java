package task;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketDataAnalyzerTest {
    @Test
    public void testCalculateMinFlightsTimes() {
        List<TicketData> tickets = List.of(
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(11, 0),
                        "S7", 0, 12400),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(9, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(12, 0),
                        "TK", 0, 13000),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(10, 0),
                        "S7", 0, 12300)
        );

        Map<String, Duration> result = TicketDataAnalyzer.calculateMinFlightsTimes(tickets, "Владивосток", "Тель-Авив");

        assertEquals(2, result.size());
        assertTrue(result.containsKey("S7"));
        assertTrue(result.containsKey("TK"));
        assertEquals(Duration.ofHours(3), result.get("S7"));
        assertEquals(Duration.ofHours(3), result.get("TK"));
    }

    @Test
    public void testCalculateAveragePrice() {
        List<TicketData> tickets = List.of(
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(11, 0),
                        "S7", 0, 12301),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(9, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(12, 0),
                        "TK", 0, 12600),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(10, 0),
                        "S7", 0, 12500)
        );

        double result = TicketDataAnalyzer.calculateAveragePrice(tickets, "Владивосток", "Тель-Авив");

        assertEquals(12467, result);
    }

    @Test
    public void testCalculateMedianPriceOddLen() {
        List<TicketData> tickets = List.of(
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(11, 0),
                        "S7", 0, 12400),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(9, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(12, 0),
                        "TK", 0, 12600),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(10, 0),
                        "S7", 0, 12500)
        );

        double result = TicketDataAnalyzer.calculateMedianPrice(tickets, "Владивосток", "Тель-Авив");

        assertEquals(12500, result);
    }

    @Test
    public void testCalculateMedianPriceEvenLen() {
        List<TicketData> tickets = List.of(
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(11, 0),
                        "S7", 0, 12400),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(9, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(12, 0),
                        "TK", 0, 12600)
        );

        double result = TicketDataAnalyzer.calculateMedianPrice(tickets, "Владивосток", "Тель-Авив");

        assertEquals(12500, result);
    }

    @Test
    public void testCalculateAverageAndMedianPriceDifference() {
        List<TicketData> tickets = List.of(
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(11, 0),
                        "S7", 0, 12400),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(9, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(12, 0),
                        "TK", 0, 12600),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(10, 0),
                        "S7", 0, 12500)
        );

        double result = TicketDataAnalyzer.calculateAverageAndMedianPriceDifference(tickets, "Владивосток", "Тель-Авив");

        assertEquals(0.0, result);
    }

    @Test
    public void testNoTickets() {
        List<TicketData> tickets = List.of();

        Map<String, Duration> minFlightTimes = TicketDataAnalyzer.calculateMinFlightsTimes(tickets, "Владивосток", "Тель-Авив");
        double averagePrice = TicketDataAnalyzer.calculateAveragePrice(tickets, "Владивосток", "Тель-Авив");
        double medianPrice = TicketDataAnalyzer.calculateMedianPrice(tickets, "Владивосток", "Тель-Авив");
        double priceDifference = TicketDataAnalyzer.calculateAverageAndMedianPriceDifference(tickets, "Владивосток", "Тель-Авив");

        assertTrue(minFlightTimes.isEmpty());
        assertEquals(Double.NaN, averagePrice);
        assertEquals(Double.NaN, medianPrice);
        assertEquals(Double.NaN, priceDifference);
    }

    @Test
    public void testInvalidCities() {
        List<TicketData> tickets = List.of(
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(11, 0),
                        "S7", 0, 12400),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(9, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(12, 0),
                        "TK", 0, 12600),
                new TicketData("VVO", "Владивосток", "TLV", "Тель-Авив",
                        LocalDate.of(2024, 7, 1), LocalTime.of(7, 0),
                        LocalDate.of(2024, 7, 1), LocalTime.of(10, 0),
                        "S7", 0, 12500)
        );

        assertTrue(TicketDataAnalyzer.calculateMinFlightsTimes(tickets, "Wrong1", "Wrong2").isEmpty());
        assertEquals(TicketDataAnalyzer.calculateAveragePrice(tickets, "Wrong1", "Wrong2"), Double.NaN);
        assertEquals(TicketDataAnalyzer.calculateMedianPrice(tickets, "Wrong1", "Wrong2"), Double.NaN);
        assertEquals(TicketDataAnalyzer.calculateAverageAndMedianPriceDifference(tickets, "Wrong1", "Wrong2"), Double.NaN);
    }
}