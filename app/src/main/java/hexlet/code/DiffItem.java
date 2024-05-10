package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public record DiffItem(
    DiffState state,
    String fieldName,
    JsonNode oldValue,
    JsonNode newValue,
    List<DiffItem> children
) {

    public static DiffItem Added(String fieldName, JsonNode newValue) {
        return new DiffItem(DiffState.ADDED, fieldName, null, newValue, null);
    }

    public static DiffItem Removed(String fieldName, JsonNode oldValue) {
        return new DiffItem(DiffState.REMOVED, fieldName, oldValue, null, null);
    }

    public static DiffItem Changed(String fieldName, JsonNode oldValue, JsonNode newValue) {
        return new DiffItem(DiffState.CHANGED, fieldName, oldValue, newValue, null);
    }

    public static DiffItem Unchanged(String fieldName, JsonNode oldValue) {
        return new DiffItem(DiffState.UNCHANGED, fieldName, oldValue, null, null);
    }

    public static DiffItem Object(String fieldName, List<DiffItem> children) {
        return new DiffItem(DiffState.OBJECT, fieldName, null, null, children);
    }

}
