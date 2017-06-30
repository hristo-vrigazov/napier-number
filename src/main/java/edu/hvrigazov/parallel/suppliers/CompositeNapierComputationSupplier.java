package edu.hvrigazov.parallel.suppliers;

import com.google.inject.Inject;
import edu.hvrigazov.parallel.parsing.CompositeComputationSettings;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.CompositeNapierComputation;
import edu.hvrigazov.parallel.run.NapierComputation;

import java.util.List;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class CompositeNapierComputationSupplier {

    private NapierComputationResultSupplier napierComputationResultSupplier;

    @Inject
    public CompositeNapierComputationSupplier(NapierComputationResultSupplier napierComputationResultSupplier) {
        this.napierComputationResultSupplier = napierComputationResultSupplier;
    }

    public CompositeNapierComputation get(CompositeComputationSettings compositeComputationSettings, List<NapierComputation> computations) {
        return new CompositeNapierComputation(compositeComputationSettings, computations, napierComputationResultSupplier);
    }
}
