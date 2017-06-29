package edu.hvrigazov.parallel.commands;

import edu.hvrigazov.parallel.parsing.ParsedOptions;

/**
 * Created by hvrigazov on 29.06.17.
 */
public interface CommandHandler {
    void handle(ParsedOptions parsedOptions);
}
