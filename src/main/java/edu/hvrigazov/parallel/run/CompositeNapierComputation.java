package edu.hvrigazov.parallel.run;

import edu.hvrigazov.parallel.parsing.CompositeComputationSettings;
import edu.hvrigazov.parallel.suppliers.NapierComputationResultSupplier;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class CompositeNapierComputation implements NapierComputation {

    private CompositeComputationSettings compositeComputationSettings;
    private List<NapierComputation> computations;
    private NapierComputationResultSupplier napierComputationResultSupplier;

    public CompositeNapierComputation(CompositeComputationSettings compositeComputationSettings,
                                      List<NapierComputation> computations,
                                      NapierComputationResultSupplier napierComputationResultSupplier) {
        this.compositeComputationSettings = compositeComputationSettings;
        this.computations = computations;
        this.napierComputationResultSupplier = napierComputationResultSupplier;
    }

    @Override
    public NapierComputationResult call() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(compositeComputationSettings.tasks());
        long startTime = Calendar.getInstance().getTimeInMillis();
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

            long elapsedTime = Calendar.getInstance().getTimeInMillis() - startTime;
            System.out.println("Max time spent by task " + maxTimePerComputation);
            System.out.println("Total time " + elapsedTime);

            PrintWriter writer = new PrintWriter(compositeComputationSettings.output(), "UTF-8");
            writer.println(result);
            writer.close();
            return napierComputationResultSupplier.get(elapsedTime, result);
        } catch (InterruptedException | ExecutionException | UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        return null;
    }
}
