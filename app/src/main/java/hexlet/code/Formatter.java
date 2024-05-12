package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {
    public static String format(DiffItem diff, String formatName) {
        var formatter = formatName.equals("plain") ? new Plain() : new Stylish();
        return formatter.format(diff);
    }
}
