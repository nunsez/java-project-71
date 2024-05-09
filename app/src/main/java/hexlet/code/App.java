package hexlet.code;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.CommandLine;

public class App {
    private static final String description = """
        gendiff [-hV]
        Compares two configuration files and shows a difference.""";

    private final static Options options = buildOptions();

    private static CommandLine cmd = CommandLine.builder().build();

    public static void main(String[] args) {
        var successParsing = parseCommand(args);
        if (!successParsing) return;

        if (cmd.hasOption("h")) {
            printHelp();
        }
    }

    private static Options buildOptions() {
        final var help = Option.builder("h")
            .longOpt("help")
            .desc("Show this help message and exit.")
            .build();

        final var version = Option.builder("V")
            .longOpt("version")
            .desc("Print version information and exit.")
            .build();

        return new Options()
            .addOption(help)
            .addOption(version);
    }

    private static boolean parseCommand(String[] args) {
        var parser = new DefaultParser();

        try {
            cmd = parser.parse(options, args);
            return true;
        } catch (ParseException e) {
            printHelp();
            return false;
        }
    }

    private static void printHelp() {
        final var formatter = new HelpFormatter();

        formatter.setSyntaxPrefix("Usage: ");
        formatter.setLongOptPrefix(" --");
        formatter.printHelp(description, options);
    }
}
