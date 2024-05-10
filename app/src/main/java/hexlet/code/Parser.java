package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode parse(String content, String format) throws JsonProcessingException {
        return switch (format) {
            case "json" -> parseJson(content);
            case "yml", "yaml" -> parseYaml();
            default -> throw new RuntimeException("Invalid format: %s".formatted(format));
        };
    }

    public static JsonNode parseJson(String content) throws JsonProcessingException {
        return MAPPER.readTree(content);
    }

    public static JsonNode parseYaml() {
        throw new IllegalCallerException();
    }

}
