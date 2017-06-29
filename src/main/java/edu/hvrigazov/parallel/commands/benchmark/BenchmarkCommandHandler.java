package edu.hvrigazov.parallel.commands.benchmark;

import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class BenchmarkCommandHandler implements CommandHandler {
    @Override
    public void handle(ParsedOptions parsedOptions) {
        System.out.println("benchmark");
    }
}
