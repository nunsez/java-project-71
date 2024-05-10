package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.formatters.Stylish;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeSet;

public class Differ {

    public static String generate(Path filepath1, Path filepath2) throws IOException {
        var content1 = Files.readString(filepath1);
        var content2 = Files.readString(filepath2);

        var node1 = Parser.parse(content1, "json");
        var node2 = Parser.parse(content2, "json");
        var diff = generateObject(null, node1, node2);

        return Stylish.format(diff);
    }

    private static DiffItem generateObject(String fieldName, JsonNode node1, JsonNode node2) {
        var keys1 = node1.fieldNames();
        var keys2 = node2.fieldNames();
        var allKeys = new TreeSet<String>();

        keys1.forEachRemaining(allKeys::add);
        keys2.forEachRemaining(allKeys::add);

        var children = allKeys.stream()
            .map((x) -> getDiffitem(x, node1, node2))
            .toList();

        return DiffItem.object(fieldName, children);
    }

    private static DiffItem getDiffitem(String fieldName, JsonNode obj1, JsonNode obj2) {
        var oldValue = obj1.get(fieldName);
        var newValue = obj2.get(fieldName);

        DiffItem item;

        if (!obj1.has(fieldName)) {
            item = DiffItem.added(fieldName, newValue);
        } else if (!obj2.has(fieldName)) {
            item = DiffItem.removed(fieldName, oldValue);
        } else if (oldValue.isContainerNode() && newValue.isContainerNode()) {
            item = generateObject(fieldName, oldValue, newValue);
        } else if (oldValue.equals(newValue)) {
            item = DiffItem.unchanged(fieldName, oldValue);
        } else {
            item = DiffItem.changed(fieldName, oldValue, newValue);
        }

        return item;
    }

}
