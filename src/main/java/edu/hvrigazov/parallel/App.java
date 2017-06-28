package edu.hvrigazov.parallel;


import edu.hvrigazov.parallel.parsing.ParsedOptions;
import org.docopt.Docopt;

import java.io.InputStream;
import java.util.Map;

/**
 * Main class
 *
 */
public class App {

    public static void main(String[] args) {
        InputStream inputStream = App.class.getResourceAsStream("/cli-specification.txt");
        Docopt docopt = new Docopt(inputStream);
        Map<String, Object> opts = docopt
                .withVersion("0.1")
                .withHelp(true)
                .withExit(true)
                .parse(args);

        ParsedOptions parsedOptions = new ParsedOptions(opts);


        System.out.println(parsedOptions.quiet());

        System.out.println(parsedOptions.precision());
        System.out.println(parsedOptions.tasks());
        System.out.println(parsedOptions.output());
    }
}
