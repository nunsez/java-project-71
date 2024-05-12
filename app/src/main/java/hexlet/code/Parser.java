package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parser {

    public static JsonNode parse(Path filepath) throws IOException {
        var normalizedPath = filepath.normalize().toAbsolutePath();
        var mapper = getMapper(normalizedPath);
        var content = Files.readString(normalizedPath);
        return mapper.readTree(content);
    }

    private static ObjectMapper getMapper(Path filepath) {
        var fileName = filepath.getFileName().toString();
        ObjectMapper mapper;

        if (fileName.endsWith(".json")) {
            mapper = new JsonMapper();
        } else if (fileName.endsWith(".yml") || fileName.endsWith(".yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("Invalid file: " + filepath.getFileName());
        }

        return mapper;
    }

}
