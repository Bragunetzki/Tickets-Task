package task;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom deserializer for {@link LocalTime} objects.
 * <p>
 * This class is used by Gson to deserialize JSON time strings into {@link LocalTime} objects
 * using the format "HH:mm" and optionally "H:mm".
 * </p>
 */
public class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H[H]:mm");

    /**
     * Deserializes a JSON element into a {@link LocalTime} object.
     *
     * @param jsonElement the JSON element to deserialize
     * @param type the type of the object to deserialize to
     * @param jsonDeserializationContext the context of the deserialization
     * @return the deserialized {@link LocalTime} object
     * @throws JsonParseException if the JSON element cannot be parsed into a {@link LocalTime}
     */
    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), formatter);
    }
}
