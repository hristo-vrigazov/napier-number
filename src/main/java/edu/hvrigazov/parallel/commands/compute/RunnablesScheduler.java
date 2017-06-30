package edu.hvrigazov.parallel.commands.compute;

import edu.hvrigazov.parallel.parsing.ComputationSettings;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.SingleNapierComputation;
import edu.hvrigazov.parallel.run.NapierComputation;
import edu.hvrigazov.parallel.suppliers.ComputationSettingsImplSuppier;
import edu.hvrigazov.parallel.suppliers.NapierComputationSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class RunnablesScheduler implements Supplier<List<NapierComputation>> {
    private List<NapierComputation> napierComputations;

    public RunnablesScheduler(NapierComputationSupplier napierComputationSupplier,
                              ComputationSettingsImplSuppier computationSettingsImplSuppier,
                              ParsedOptions computationSettings) {
        this.napierComputations = new ArrayList<>();

        int intervalSize = (computationSettings.to() - computationSettings.from()) / computationSettings.tasks();

        for (int i = 0; i < computationSettings.tasks() - 1; i++) {
            boolean quiet = computationSettings.quiet();
            int from = i * intervalSize;
            int to = from + intervalSize;
            ComputationSettings computationSettingsSubtask = computationSettingsImplSuppier.get(quiet, from, to);
            SingleNapierComputation napierComputation = napierComputationSupplier.get(computationSettingsSubtask);
            this.napierComputations.add(napierComputation);
        }

        boolean quiet = computationSettings.quiet();
        int from = (computationSettings.tasks() - 1) * intervalSize;
        int to = computationSettings.to();
        ComputationSettings computationSettingsSubtask = computationSettingsImplSuppier.get(quiet, from, to);
        SingleNapierComputation napierComputation = napierComputationSupplier.get(computationSettingsSubtask);
        this.napierComputations.add(napierComputation);

    }

    @Override
    public List<NapierComputation> get() {
        return napierComputations;
    }
}
