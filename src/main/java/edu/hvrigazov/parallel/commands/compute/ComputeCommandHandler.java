package edu.hvrigazov.parallel.commands.compute;

import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.NapierComputation;
import edu.hvrigazov.parallel.run.NapierComputationResult;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class ComputeCommandHandler implements CommandHandler {
    @Override
    public void handle(ParsedOptions parsedOptions) {
        System.out.println("compute");
        RunnablesScheduler runnablesScheduler = new RunnablesScheduler(parsedOptions);
        List<NapierComputation> computations = runnablesScheduler.get();

        ExecutorService executorService = Executors.newFixedThreadPool(parsedOptions.tasks());
        try {
            List<Future<NapierComputationResult>> futures = executorService.invokeAll(computations);

            BigDecimal result = BigDecimal.ZERO;
            long maxTimePerComputation = 0;
            for (Future<NapierComputationResult> future: futures) {
                NapierComputationResult napierComputationResult = future.get();
                result = result.add(napierComputationResult.getResult());

                if (napierComputationResult.getExecutionTime() > maxTimePerComputation) {
                    maxTimePerComputation = napierComputationResult.getExecutionTime();
                }
            }

            System.out.println(result);
            System.out.println(maxTimePerComputation);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
