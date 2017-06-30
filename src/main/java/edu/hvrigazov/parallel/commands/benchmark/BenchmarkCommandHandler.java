package edu.hvrigazov.parallel.commands.benchmark;

import com.google.inject.Inject;
import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.commands.compute.RunnablesScheduler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.CompositeNapierComputation;
import edu.hvrigazov.parallel.run.NapierComputation;
import edu.hvrigazov.parallel.suppliers.CompositeNapierComputationSupplier;
import edu.hvrigazov.parallel.suppliers.ComputationSettingsImplSuppier;
import edu.hvrigazov.parallel.suppliers.RunnablesSchedulerSupplier;

import java.util.List;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class BenchmarkCommandHandler implements CommandHandler {

    private RunnablesSchedulerSupplier runnablesSchedulerSupplier;
    private CompositeNapierComputationSupplier compositeNapierComputationSupplier;
    private ComputationSettingsImplSuppier computationSettingsImplSuppier;

    @Inject
    public BenchmarkCommandHandler(RunnablesSchedulerSupplier runnablesSchedulerSupplier, CompositeNapierComputationSupplier compositeNapierComputationSupplier, ComputationSettingsImplSuppier computationSettingsImplSuppier) {
        this.runnablesSchedulerSupplier = runnablesSchedulerSupplier;
        this.compositeNapierComputationSupplier = compositeNapierComputationSupplier;
        this.computationSettingsImplSuppier = computationSettingsImplSuppier;
    }

    @Override
    public void handle(ParsedOptions parsedOptions) {
        System.out.println("benchmark");

        for (int i = 1; i <= parsedOptions.tasks(); i++) {
            System.out.println("Run with number of tasks: " + i);
            ParsedOptions newParsedOptions = parsedOptions.withTasks(i);
            RunnablesScheduler runnablesScheduler = runnablesSchedulerSupplier.get(newParsedOptions);
            List<NapierComputation> computations = runnablesScheduler.get();

            CompositeNapierComputation compositeNapierComputation = compositeNapierComputationSupplier.get(newParsedOptions, computations);
            try {
                compositeNapierComputation.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
