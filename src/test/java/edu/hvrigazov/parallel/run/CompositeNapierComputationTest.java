package edu.hvrigazov.parallel.run;

import edu.hvrigazov.parallel.parsing.CompositeComputationSettings;
import edu.hvrigazov.parallel.suppliers.NapierComputationResultSupplier;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class CompositeNapierComputationTest {

    File tempFile;

    @Test
    public void computesCorrectlyWith3Threads() throws Exception {
        tempFile = File.createTempFile("temp", Long.toString(System.nanoTime()));
        BigDecimal expected = new BigDecimal(3.5);

        CompositeComputationSettings parsedOptions = mock(CompositeComputationSettings.class);
        when(parsedOptions.tasks()).thenReturn(2);
        when(parsedOptions.output()).thenReturn(tempFile.getAbsolutePath());

        NapierComputation firstSubComputation = mock(NapierComputation.class, RETURNS_DEEP_STUBS);
        when(firstSubComputation.call().getResult()).thenReturn(new BigDecimal(2.0));
        when(firstSubComputation.call().getExecutionTime()).thenReturn(100L);


        NapierComputation secondSubComputation = mock(NapierComputation.class, RETURNS_DEEP_STUBS);
        when(secondSubComputation.call().getResult()).thenReturn(new BigDecimal(0.5));
        when(secondSubComputation.call().getExecutionTime()).thenReturn(200L);

        NapierComputation thirdSubComputation = mock(NapierComputation.class, RETURNS_DEEP_STUBS);
        when(thirdSubComputation.call().getResult()).thenReturn(new BigDecimal(1.0));
        when(thirdSubComputation.call().getExecutionTime()).thenReturn(100L);

        List<NapierComputation> napierComputationList = new ArrayList<>();
        napierComputationList.add(firstSubComputation);
        napierComputationList.add(secondSubComputation);
        napierComputationList.add(thirdSubComputation);

        NapierComputationResultSupplier napierComputationResultSupplier = mock(NapierComputationResultSupplier.class);

        CompositeNapierComputation napierComputation = new CompositeNapierComputation(parsedOptions,
                napierComputationList, napierComputationResultSupplier);

        napierComputation.call();

        verify(napierComputationResultSupplier).get(anyLong(), eq(expected));
    }

    @Test
    public void whenExceptionIsThrown() throws Exception {
        tempFile = File.createTempFile("temp", Long.toString(System.nanoTime()));
        BigDecimal expected = new BigDecimal(3.5);

        CompositeComputationSettings parsedOptions = mock(CompositeComputationSettings.class);
        when(parsedOptions.tasks()).thenReturn(1);
        when(parsedOptions.output()).thenReturn(tempFile.getAbsolutePath());

        NapierComputation firstSubComputation = mock(NapierComputation.class, RETURNS_DEEP_STUBS);
        when(firstSubComputation.call()).thenThrow(new InterruptedException());

        List<NapierComputation> napierComputationList = new ArrayList<>();
        napierComputationList.add(firstSubComputation);

        NapierComputationResultSupplier napierComputationResultSupplier = mock(NapierComputationResultSupplier.class);

        CompositeNapierComputation napierComputation = new CompositeNapierComputation(parsedOptions,
                napierComputationList, napierComputationResultSupplier);

        napierComputation.call();
    }

    @After
    public void cleanUp() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
}
