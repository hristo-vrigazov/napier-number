package edu.hvrigazov.parallel.commands.compute;

import com.google.inject.Inject;
import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.CompositeNapierComputation;
import edu.hvrigazov.parallel.run.NapierComputation;
import edu.hvrigazov.parallel.suppliers.CompositeNapierComputationSupplier;
import edu.hvrigazov.parallel.suppliers.RunnablesSchedulerSupplier;

import java.util.List;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class ComputeCommandHandler implements CommandHandler {

    private RunnablesSchedulerSupplier runnablesSchedulerSupplier;
    private CompositeNapierComputationSupplier compositeNapierComputationSupplier;

    @Inject
    public ComputeCommandHandler(RunnablesSchedulerSupplier runnablesSchedulerSupplier,
                                 CompositeNapierComputationSupplier compositeNapierComputationSupplier) {
        this.runnablesSchedulerSupplier = runnablesSchedulerSupplier;
        this.compositeNapierComputationSupplier = compositeNapierComputationSupplier;
    }

    @Override
    public void handle(ParsedOptions parsedOptions) {
        System.out.println("compute");
        RunnablesScheduler runnablesScheduler = runnablesSchedulerSupplier.get(parsedOptions);
        List<NapierComputation> computations = runnablesScheduler.get();

        CompositeNapierComputation compositeNapierComputation = compositeNapierComputationSupplier.get(parsedOptions, computations);
        try {
            compositeNapierComputation.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
