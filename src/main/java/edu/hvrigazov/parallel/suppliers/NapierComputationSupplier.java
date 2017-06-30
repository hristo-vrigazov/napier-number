package edu.hvrigazov.parallel.suppliers;

import com.google.inject.Inject;
import edu.hvrigazov.parallel.parsing.ComputationSettings;
import edu.hvrigazov.parallel.run.SingleNapierComputation;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class NapierComputationSupplier {

    private NapierComputationResultSupplier napierComputationResultSupplier;

    @Inject
    public NapierComputationSupplier(NapierComputationResultSupplier napierComputationResultSupplier) {
        this.napierComputationResultSupplier = napierComputationResultSupplier;
    }

    public SingleNapierComputation get(ComputationSettings computationSettings) {
        return new SingleNapierComputation(computationSettings, napierComputationResultSupplier);
    }
}
