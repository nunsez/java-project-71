package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.Callable;

@Command(
    name = "gendiff",
    description = "Compares two configuration files and shows a difference.",
    sortOptions = false
)
public class Cli implements Callable<Integer> {

    @Option(
        names = { "-f", "--format" },
        description = "output format [default: ${DEFAULT-VALUE}]",
        defaultValue = "stylish",
        paramLabel = "format"
    )
    String format;

    @Option(
        names = { "-h", "--help" },
        description = "Show this help message and exit.",
        usageHelp = true
    )
    boolean help;

    @Option(
        names = { "-V", "--version" },
        description = "Print version information and exit."
    )
    boolean version;

    @Parameters(
        index = "0",
        description = "path to first file",
        paramLabel = "filepath1",
        defaultValue = Option.NULL_VALUE
    )
    Optional<File> filepath1;

    @Parameters(
        index = "1",
        description = "path to second file",
        paramLabel = "filepath2",
        defaultValue = Option.NULL_VALUE
    )
    Optional<File> filepath2;

    @Override
    public Integer call() {
        return 0;
    }
}
