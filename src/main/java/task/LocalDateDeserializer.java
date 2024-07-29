package task;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Custom deserializer for {@link LocalDate} objects.
 * <p>
 * This class is used by Gson to deserialize JSON date strings into {@link LocalDate} objects
 * using the format "dd.MM.yy".
 * </p>
 */
public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    /**
     * Deserializes a JSON element into a {@link LocalDate} object.
     *
     * @param jsonElement the JSON element to deserialize
     * @param type the type of the object to deserialize to
     * @param jsonDeserializationContext the context of the deserialization
     * @return the deserialized {@link LocalDate} object
     * @throws JsonParseException if the JSON element cannot be parsed into a {@link LocalDate}
     */
    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), formatter);
    }
}
