package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        var node1 = Parser.parse(filePath1);
        var node2 = Parser.parse(filePath2);

        var diff = generateObject(null, node1, node2);

        return Formatter.format(diff, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
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
        } else if (oldValue.isObject() && newValue.isObject()) {
            item = generateObject(fieldName, oldValue, newValue);
        } else if (oldValue.equals(newValue)) {
            item = DiffItem.unchanged(fieldName, oldValue);
        } else {
            item = DiffItem.changed(fieldName, oldValue, newValue);
        }

        return item;
    }

}
