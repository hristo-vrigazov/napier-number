package edu.hvrigazov.parallel.commands.compute;

import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.CompositeNapierComputation;
import edu.hvrigazov.parallel.run.NapierComputation;
import edu.hvrigazov.parallel.suppliers.CompositeNapierComputationSupplier;
import edu.hvrigazov.parallel.suppliers.RunnablesSchedulerSupplier;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class ComputeCommandHandlerTest {

    @Test
    public void invokesCompositeComputation() throws Exception {
        List<NapierComputation> napierComputationList = new ArrayList<>();
        RunnablesScheduler runnablesScheduler = mock(RunnablesScheduler.class);
        when(runnablesScheduler.get()).thenReturn(napierComputationList);
        ParsedOptions parsedOptions = mock(ParsedOptions.class);
        RunnablesSchedulerSupplier runnablesSchedulerSupplier = mock(RunnablesSchedulerSupplier.class);
        when(runnablesSchedulerSupplier.get(parsedOptions)).thenReturn(runnablesScheduler);
        CompositeNapierComputationSupplier compositeNapierComputationSupplier = mock(CompositeNapierComputationSupplier.class);
        CompositeNapierComputation compositeNapierComputation = mock(CompositeNapierComputation.class);
        when(compositeNapierComputationSupplier.get(parsedOptions, napierComputationList)).thenReturn(compositeNapierComputation);

        ComputeCommandHandler computeCommandHandler = new ComputeCommandHandler(runnablesSchedulerSupplier, compositeNapierComputationSupplier);
        computeCommandHandler.handle(parsedOptions);

        verify(compositeNapierComputation).call();
    }

    @Test
    public void exceptionIsLogged() throws Exception {
        List<NapierComputation> napierComputationList = new ArrayList<>();
        RunnablesScheduler runnablesScheduler = mock(RunnablesScheduler.class);
        when(runnablesScheduler.get()).thenReturn(napierComputationList);
        ParsedOptions parsedOptions = mock(ParsedOptions.class);
        RunnablesSchedulerSupplier runnablesSchedulerSupplier = mock(RunnablesSchedulerSupplier.class);
        when(runnablesSchedulerSupplier.get(parsedOptions)).thenReturn(runnablesScheduler);
        CompositeNapierComputationSupplier compositeNapierComputationSupplier = mock(CompositeNapierComputationSupplier.class);
        CompositeNapierComputation compositeNapierComputation = mock(CompositeNapierComputation.class);
        when(compositeNapierComputation.call()).thenThrow(new InterruptedException());
        when(compositeNapierComputationSupplier.get(parsedOptions, napierComputationList)).thenReturn(compositeNapierComputation);

        ComputeCommandHandler computeCommandHandler = new ComputeCommandHandler(runnablesSchedulerSupplier, compositeNapierComputationSupplier);
        computeCommandHandler.handle(parsedOptions);

        verify(compositeNapierComputation).call();
    }
}
