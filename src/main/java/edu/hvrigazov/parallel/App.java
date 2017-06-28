package edu.hvrigazov.parallel;


import edu.hvrigazov.parallel.parsing.ParsedOptions;
import org.apfloat.Apfloat;
import org.apfloat.ApintMath;
import org.docopt.Docopt;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

        double e = 0.0;

        long startNano = System.nanoTime();
        for (int k = 0; k < parsedOptions.precision(); k++) {
            System.out.println(k);
            e += (2.0 * k + 1.0) / ApintMath.factorial(2 * k).doubleValue();
        }

        System.out.println("Elapsed: " + String.valueOf((System.nanoTime() - startNano) / 10.0e9) + " s");
        System.out.println(e);
    }
}
