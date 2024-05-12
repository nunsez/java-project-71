package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.DiffItem;

public interface Formatter {
    String format(DiffItem diff) throws JsonProcessingException;
}
