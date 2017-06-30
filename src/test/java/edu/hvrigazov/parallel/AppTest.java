package edu.hvrigazov.parallel;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import edu.hvrigazov.parallel.commands.CommandHandler;
import edu.hvrigazov.parallel.commands.benchmark.BenchmarkCommandHandler;
import edu.hvrigazov.parallel.commands.compute.ComputeCommandHandler;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link App} class
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        Guice.class
})
public class AppTest {

//    private final ByteArrayOutputStream outContent = spy(new ByteArrayOutputStream());
//    private final ByteArrayOutputStream errContent = spy(new ByteArrayOutputStream());
//
//    @Before
//    public void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @After
//    public void cleanUpStreams() {
//        System.setOut(null);
//        System.setErr(null);
//    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void exitsJVMIfHelpIsRequested() {
        exit.expectSystemExitWithStatus(0);
        String[] arguments = {
                "-h"
        };
        App.main(arguments);
    }

    @Test
    public void exitsWithSuccessStatusCodeIfVersionIsRequested() {
        exit.expectSystemExitWithStatus(0);
        String[] arguments = {
                "--version"
        };

        App.main(arguments);

    }

    @Test
    public void correctlyInvokesComputeHandler() throws Exception {
        ComputeCommandHandler computeCommandHandler = mock(ComputeCommandHandler.class);
        BenchmarkCommandHandler benchmarkCommandHandler = mock(BenchmarkCommandHandler.class);

        Injector injector = mock(Injector.class);
        when(injector.getInstance(ComputeCommandHandler.class)).thenReturn(computeCommandHandler);
        when(injector.getInstance(BenchmarkCommandHandler.class)).thenReturn(benchmarkCommandHandler);

        PowerMockito.mockStatic(Guice.class);
        when(Guice.createInjector(any(Module.class))).thenReturn(injector);

        String[] arguments = {
                "compute",
                "-p", "1000",
                "-t", "1",
                "-o", "output.txt",
                "-q"
        };

        App.main(arguments);

        verify(computeCommandHandler).handle(any(ParsedOptions.class));
    }
}
