package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private final static Path workingDir = Path.of("src/test/resources/hexlet/code");

    @Test
    void generatePlain() throws IOException {
        var filepath1 = resourcePath("plain-1.json");
        var filepath2 = resourcePath("plain-2.json");
        var result = resourceContent("plain-result.txt");

        var check = Differ.generate(filepath1, filepath2);

        assertThat(check).isEqualTo(result);
    }

    private Path resourcePath(String relativePath) {
        return workingDir.resolve(relativePath);
    }

    private String resourceContent(String relativePath) {
        var path = resourcePath(relativePath);

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
