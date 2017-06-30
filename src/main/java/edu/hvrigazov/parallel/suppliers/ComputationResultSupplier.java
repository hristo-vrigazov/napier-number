package edu.hvrigazov.parallel.suppliers;

import edu.hvrigazov.parallel.run.NapierComputationResult;

import java.math.BigDecimal;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class ComputationResultSupplier {
    public NapierComputationResult get(long executionTime, BigDecimal result) {
        return new NapierComputationResult(executionTime, result);
    }
}
