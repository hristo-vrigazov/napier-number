package edu.hvrigazov.parallel;


import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.commands.benchmark.BenchmarkCommandHandler;
import edu.hvrigazov.parallel.commands.compute.ComputeCommandHandler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.NapierComputation;
import org.docopt.Docopt;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Main class
 *
 */
public class App {

    public static class Commands {
        public final static String COMPUTE = "compute";
        public final static String BENCHMARK = "benchmark";
    }

    public static void main(String[] args) {
        InputStream inputStream = App.class.getResourceAsStream("/cli-specification.txt");
        Docopt docopt = new Docopt(inputStream);
        Map<String, Object> opts = docopt
                .withVersion("0.1")
                .withHelp(true)
                .withExit(true)
                .parse(args);

        ParsedOptions parsedOptions = new ParsedOptions(opts);

        Map<String, CommandHandler> commandToHandler = getCommands();
        Optional<String> optionalSelectedCommand = commandToHandler
                .keySet()
                .stream()
                .filter(command -> opts.get(command).equals(true))
                .findAny();

        optionalSelectedCommand.ifPresent(command -> commandToHandler.get(command).handle(parsedOptions));
    }

    private static Map<String, CommandHandler> getCommands() {
        Map<String, CommandHandler> map = new HashMap<>();
        map.put(Commands.COMPUTE, new ComputeCommandHandler());
        map.put(Commands.BENCHMARK, new BenchmarkCommandHandler());
        return map;
    }
}
