package edu.hvrigazov.parallel.parsing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class ComputationSettingsImplTest {

    @Test
    public void correctlyExposesArguments() {
        boolean expectedQuiet = true;
        Integer expectedFrom = 0;
        Integer expectedTo = 100;
        ComputationSettingsImpl computationSettings = new ComputationSettingsImpl(expectedQuiet, expectedFrom, expectedTo);

        assertEquals(expectedQuiet, computationSettings.quiet());
        assertEquals(expectedFrom, computationSettings.from());
        assertEquals(expectedTo, computationSettings.to());
    }
}
