package hexlet.code.formatters;

import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.DiffItem;
import java.util.stream.Collectors;

public class Stylish {

    private static final String INDENT = " ";

    private static final int INDENT_COUNT = 2;

    public static String format(DiffItem diff) {
        return genStylishLine(diff, 0);
    }

    private static String genStylishLine(DiffItem diff, int depth) {
        var fieldName = diff.fieldName();
        var sb = new StringBuilder();
        var indent = INDENT.repeat(depth * INDENT_COUNT);

        switch (diff.state()) {
            case ADDED -> {
                var newValue = nodeToString(diff.newValue());
                sb.append(indent).append("+ ").append(fieldName).append(": ").append(newValue);
            }
            case REMOVED -> {
                var oldValue = nodeToString(diff.oldValue());
                sb.append(indent).append("- ").append(fieldName).append(": ").append(oldValue);
            }
            case CHANGED -> {
                var oldValue = nodeToString(diff.oldValue());
                var newValue = nodeToString(diff.newValue());
                sb
                    .append(indent).append("- ").append(fieldName).append(": ").append(oldValue)
                    .append("\n")
                    .append(indent).append("+ ").append(fieldName).append(": ").append(newValue);
            }
            case UNCHANGED -> {
                var oldValue = nodeToString(diff.oldValue());
                sb.append(indent).append("  ").append(fieldName).append(": ").append(oldValue);
            }
            case OBJECT -> {
                sb.append(indent);
                if (fieldName != null) {
                    sb.append(fieldName).append(": ");
                }
                sb.append("{\n");

                var nestedObjectFields = diff.children().stream()
                    .map((childDiff) -> genStylishLine(childDiff, depth + 1))
                    .collect(Collectors.joining("\n"));

                sb.append(nestedObjectFields);
                sb.append(indent).append("\n}");
            }
            default -> throw new IllegalArgumentException("Invalid state: " + diff.state());
        }

        return sb.toString();
    }

    private static String nodeToString(JsonNode node) {
        return switch (node.getNodeType()) {
            case OBJECT -> jsObjectToString(node);
            case ARRAY -> jsArrayToString(node);
            default -> node.asText();
        };
    }

    private static String jsArrayToString(JsonNode node) {
        return node.toPrettyString();
    }

    private static String jsObjectToString(JsonNode node) {
        return node.toPrettyString();
    }
}
