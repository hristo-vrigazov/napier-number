package edu.hvrigazov.parallel.parsing;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static edu.hvrigazov.parallel.parsing.Options.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class ParsedOptionsTest {

    @Test
    public void parsesOptions() {
        boolean expectedQuiet = true;
        Integer expectedFrom = 0;
        Integer expectedTo = 100;
        Integer expectedTasks = 4;
        String expectedOutput = "output.txt";

        Map<String, Object> opts = new HashMap<>();
        opts.put(QUIET, expectedQuiet);
        opts.put(PRECISION, "100");
        opts.put(TASKS, "4");
        opts.put(OUTPUT, expectedOutput);

        ParsedOptions parsedOptions = new ParsedOptions(opts);

        assertEquals(expectedQuiet, parsedOptions.quiet());
        assertEquals(expectedFrom, parsedOptions.from());
        assertEquals(expectedTo, parsedOptions.to());
        assertEquals(expectedTasks, parsedOptions.tasks());
        assertEquals(expectedOutput, parsedOptions.output());
    }
}
