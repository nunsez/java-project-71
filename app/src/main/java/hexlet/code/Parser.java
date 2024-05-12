package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Parser {

    public static JsonNode parse(String filePath) throws IOException {
        var normalizedPath = Path.of(filePath).normalize().toAbsolutePath();
        var mapper = getMapper(normalizedPath);
        var content = Files.readString(normalizedPath);
        return mapper.readTree(content);
    }

    private static ObjectMapper getMapper(Path filePath) {
        var fileName = filePath.getFileName().toString();
        ObjectMapper mapper;

        if (fileName.endsWith(".json")) {
            mapper = new JsonMapper();
        } else if (fileName.endsWith(".yml") || fileName.endsWith(".yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("Invalid file: " + filePath.getFileName());
        }

        return mapper;
    }

}
