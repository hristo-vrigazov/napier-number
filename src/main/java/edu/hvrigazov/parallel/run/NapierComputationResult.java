package edu.hvrigazov.parallel.run;

import java.math.BigDecimal;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class NapierComputationResult {

    private Integer numberOfTasks;
    private long executionTime;
    private BigDecimal result;

    public NapierComputationResult(Integer numberOfTasks, long executionTime, BigDecimal result) {
        this.numberOfTasks = numberOfTasks;
        this.executionTime = executionTime;
        this.result = result;
    }

    public Integer getNumberOfTasks() {
        return numberOfTasks;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public BigDecimal getResult() {
        return result;
    }
}
