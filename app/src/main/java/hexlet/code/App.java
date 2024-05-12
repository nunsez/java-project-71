package hexlet.code;

import picocli.CommandLine;

public final class App {
    public static void main(String[] args) {
        var cli = new Cli();
        var cmd = new CommandLine(cli);
        var exitCode = cmd.execute(args);
        System.exit(exitCode);
    }
}
