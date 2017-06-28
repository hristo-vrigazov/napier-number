package edu.hvrigazov.parallel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.spy;

/**
 * Unit test for {@link App} class
 */
public class AppTest {

    private final ByteArrayOutputStream outContent = spy(new ByteArrayOutputStream());
    private final ByteArrayOutputStream errContent = spy(new ByteArrayOutputStream());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

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
    public void correctlyParsesArguments() {
        // does not exit system
        String[] arguments = {
                "-p", "10240",
                "-t", "2",
                "-o", "output.txt",
                "-q"
        };
        App.main(arguments);
    }
}
