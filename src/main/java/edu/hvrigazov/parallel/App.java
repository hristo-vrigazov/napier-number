package edu.hvrigazov.parallel;


import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.commands.benchmark.BenchmarkCommandHandler;
import edu.hvrigazov.parallel.commands.compute.ComputeCommandHandler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import org.docopt.Docopt;

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

        Injector injector = Guice.createInjector(new DefaultDependenciesModule());
        Map<String, CommandHandler> commandToHandler = getCommands(injector);
        Optional<String> optionalSelectedCommand = commandToHandler
                .keySet()
                .stream()
                .filter(command -> opts.get(command).equals(true))
                .findAny();

        optionalSelectedCommand.ifPresent(command -> commandToHandler.get(command).handle(parsedOptions));
    }

    public static Map<String, CommandHandler> getCommands(Injector injector) {
        Map<String, CommandHandler> map = new HashMap<>();
        map.put(Commands.COMPUTE, injector.getInstance(ComputeCommandHandler.class));
        map.put(Commands.BENCHMARK, injector.getInstance(BenchmarkCommandHandler.class));
        return map;
    }
}
