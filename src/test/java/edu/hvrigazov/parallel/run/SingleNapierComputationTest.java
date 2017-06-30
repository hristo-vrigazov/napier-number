package edu.hvrigazov.parallel.run;

import edu.hvrigazov.parallel.parsing.ComputationSettings;
import edu.hvrigazov.parallel.suppliers.NapierComputationResultSupplier;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class SingleNapierComputationTest {

    @Test
    public void correctlyComputes2Terms() throws Exception {
        BigDecimal expected = new BigDecimal(2.5);
        ComputationSettings computationSettings = mock(ComputationSettings.class);
        when(computationSettings.from()).thenReturn(0);
        when(computationSettings.to()).thenReturn(2);

        NapierComputationResultSupplier napierComputationResultSupplier = mock(NapierComputationResultSupplier.class);

        SingleNapierComputation napierComputation = new SingleNapierComputation(computationSettings, napierComputationResultSupplier);
        napierComputation.call();

        verify(napierComputationResultSupplier).get(anyLong(), eq(expected));
    }
}
