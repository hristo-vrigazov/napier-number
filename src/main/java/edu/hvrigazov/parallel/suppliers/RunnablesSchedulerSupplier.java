package edu.hvrigazov.parallel.suppliers;

import com.google.inject.Inject;
import edu.hvrigazov.parallel.commands.compute.RunnablesScheduler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class RunnablesSchedulerSupplier {

    private NapierComputationSupplier napierComputationSupplier;
    private ComputationSettingsImplSuppier computationSettingsImplSuppier;

    @Inject
    public RunnablesSchedulerSupplier(NapierComputationSupplier napierComputationSupplier, ComputationSettingsImplSuppier computationSettingsImplSuppier) {
        this.napierComputationSupplier = napierComputationSupplier;
        this.computationSettingsImplSuppier = computationSettingsImplSuppier;
    }

    public RunnablesScheduler get(ParsedOptions parsedOptions) {
        return new RunnablesScheduler(napierComputationSupplier, computationSettingsImplSuppier, parsedOptions);
    }
}
