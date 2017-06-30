package edu.hvrigazov.parallel.commands.compute;

import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.NapierComputation;
import edu.hvrigazov.parallel.suppliers.ComputationSettingsImplSuppier;
import edu.hvrigazov.parallel.suppliers.NapierComputationResultSupplier;
import edu.hvrigazov.parallel.suppliers.NapierComputationSupplier;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class RunnablesSchedulerTest {

    @Test
    public void correctlySuppliesNapierComputations() {
        int expectedSize = 3;

        NapierComputationSupplier napierComputationSupplier = mock(NapierComputationSupplier.class);
        ComputationSettingsImplSuppier computationSettingsImplSuppier = mock(ComputationSettingsImplSuppier.class);
        ParsedOptions parsedOptions = mock(ParsedOptions.class);
        when(parsedOptions.tasks()).thenReturn(expectedSize);

        RunnablesScheduler runnablesScheduler = new RunnablesScheduler(napierComputationSupplier,
                computationSettingsImplSuppier, parsedOptions);

        List<NapierComputation> computations = runnablesScheduler.get();

        assertEquals(expectedSize, computations.size());
    }
}
