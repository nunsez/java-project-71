package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private static final Path WORKING_DIR = Path.of("src/test/resources/hexlet/code");

    @Test
    void generateStylishJsonAsDefault() throws IOException {
        var filePath1 = resourcePath("file1.json");
        var filePath2 = resourcePath("file2.json");
        var result = resourceContent("stylish-result.txt");

        var check = Differ.generate(filePath1, filePath2);

        assertThat(check).isEqualTo(result);
    }

    @Test
    void generateStylishYaml() throws IOException {
        var filePath1 = resourcePath("file1.yml");
        var filePath2 = resourcePath("file2.yaml");
        var result = resourceContent("stylish-result.txt");

        var check = Differ.generate(filePath1, filePath2, "stylish");

        assertThat(check).isEqualTo(result);
    }

    @Test
    void generatePlainJson() throws IOException {
        var filePath1 = resourcePath("file1.json");
        var filePath2 = resourcePath("file2.json");
        var result = resourceContent("plain-result.txt");

        var check = Differ.generate(filePath1, filePath2, "plain");

        assertThat(check).isEqualTo(result);
    }

    @Test
    void generatePlainYaml() throws IOException {
        var filePath1 = resourcePath("file1.yml");
        var filePath2 = resourcePath("file2.yaml");
        var result = resourceContent("plain-result.txt");

        var check = Differ.generate(filePath1, filePath2, "plain");

        assertThat(check).isEqualTo(result);
    }

    @Test
    void generateJsonJson() throws IOException {
        var filePath1 = resourcePath("file1.json");
        var filePath2 = resourcePath("file2.json");

        var check = Differ.generate(filePath1, filePath2, "json");

        assertThat(check).isNotBlank();
    }

    private String resourcePath(String relativePath) {
        return WORKING_DIR.resolve(relativePath).toString();
    }

    private String resourceContent(String relativePath) {
        var path = resourcePath(relativePath);

        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
