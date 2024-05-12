package hexlet.code.formatters;

import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.DiffItem;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Plain implements Formatter {

    private static final String DELIMITER = "\n";

    private static final String ADDED_MESSAGE = "Property '%s' was added with value: %s";

    private static final String REMOVED_MESSAGE = "Property '%s' was removed";

    private static final String CHANGED_MESSAGE = "Property '%s' was updated. From %s to %s";

    @Override
    public String format(DiffItem diff) {
        return diff.children().stream()
            .map(this::formatDiffItem)
            .filter(el -> !el.isBlank())
            .collect(Collectors.joining(DELIMITER));
    }

    private String formatDiffItem(DiffItem diff) {
        var result = new StringJoiner(DELIMITER);

        switch (diff.state()) {
            case ADDED -> {
                var newValue = nodeToString(diff.newValue());
                var message = ADDED_MESSAGE.formatted(diff.fieldName(), newValue);
                result.add(message);
            }
            case REMOVED -> {
                var message = REMOVED_MESSAGE.formatted(diff.fieldName());
                result.add(message);
            }
            case CHANGED -> {
                var oldValue = nodeToString(diff.oldValue());
                var newValue = nodeToString(diff.newValue());
                var message = CHANGED_MESSAGE.formatted(diff.fieldName(), oldValue, newValue);
                result.add(message);
            }
            case OBJECT -> {
                var messages = format(diff);
                result.add(messages);
            }
            default -> {
                // ignore
            }
        }

        return result.toString();
    }

    private static String nodeToString(JsonNode node) {
        return switch (node.getNodeType()) {
            case OBJECT, ARRAY -> "[complex value]";
            case STRING -> "'" + node.asText() + "'";
            default -> node.asText();
        };
    }
}
