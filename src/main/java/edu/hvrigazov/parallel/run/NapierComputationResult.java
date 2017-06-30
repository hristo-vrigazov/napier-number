package edu.hvrigazov.parallel.run;

import java.math.BigDecimal;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class NapierComputationResult {

    private long executionTime;
    private BigDecimal result;

    public NapierComputationResult(long executionTime, BigDecimal result) {
        this.executionTime = executionTime;
        this.result = result;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public BigDecimal getResult() {
        return result;
    }
}
