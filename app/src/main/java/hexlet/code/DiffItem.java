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

    public static DiffItem added(String fieldName, JsonNode newValue) {
        return new DiffItem(DiffState.ADDED, fieldName, null, newValue, null);
    }

    public static DiffItem removed(String fieldName, JsonNode oldValue) {
        return new DiffItem(DiffState.REMOVED, fieldName, oldValue, null, null);
    }

    public static DiffItem changed(String fieldName, JsonNode oldValue, JsonNode newValue) {
        return new DiffItem(DiffState.CHANGED, fieldName, oldValue, newValue, null);
    }

    public static DiffItem unchanged(String fieldName, JsonNode oldValue) {
        return new DiffItem(DiffState.UNCHANGED, fieldName, oldValue, null, null);
    }

    public static DiffItem object(String fieldName, List<DiffItem> children) {
        return new DiffItem(DiffState.OBJECT, fieldName, null, null, children);
    }

}
