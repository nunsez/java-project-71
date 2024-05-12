package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(
    name = "gendiff",
    description = "Compares two configuration files and shows a difference.",
    sortOptions = false,
    version = "gendiff 0.0.1"
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
        description = "Print version information and exit.",
        versionHelp = true
    )
    boolean version;

    @Parameters(
        index = "0",
        description = "path to first file",
        paramLabel = "filePath1"
    )
    String filePath1;

    @Parameters(
        index = "1",
        description = "path to second file",
        paramLabel = "filePath2"
    )
    String filePath2;

    @Override
    public Integer call() throws IOException {
        var diff = Differ.generate(filePath1, filePath2, format);
        System.out.println(diff);
        return 0;
    }
}
