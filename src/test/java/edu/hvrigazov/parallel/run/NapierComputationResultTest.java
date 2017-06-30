package edu.hvrigazov.parallel.run;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class NapierComputationResultTest {

    @Test
    public void correctlyExposesParameters() {
        long duration = 1000;
        BigDecimal result = new BigDecimal(2.71);
        NapierComputationResult napierComputationResult = new NapierComputationResult(duration, result);

        assertEquals(duration, napierComputationResult.getExecutionTime());
        assertEquals(result, napierComputationResult.getResult());
    }
}
