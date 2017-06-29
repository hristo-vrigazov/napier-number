package edu.hvrigazov.parallel.commands.compute;

import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.NapierComputation;
import edu.hvrigazov.parallel.run.NapierComputationResult;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class ComputeCommandHandler implements CommandHandler {
    @Override
    public void handle(ParsedOptions parsedOptions) {
        System.out.println("compute");
        NapierComputation napierComputation = new NapierComputation(parsedOptions);
        Thread thread = new Thread(napierComputation);

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // write to file
        try {
            PrintWriter writer = new PrintWriter(parsedOptions.output(), "UTF-8");
            writer.println(napierComputation.getResult().getResult());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
