package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public final class Formatter {

    public static String format(DiffItem diff, String formatName) throws JsonProcessingException {
        var formatter = switch (formatName) {
            case "plain" -> new Plain();
            case "json" -> new Json();
            default -> new Stylish();
        };

        return formatter.format(diff);
    }

}
