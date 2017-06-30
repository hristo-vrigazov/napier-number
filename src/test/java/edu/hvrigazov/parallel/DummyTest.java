package edu.hvrigazov.parallel;

import edu.hvrigazov.parallel.parsing.Options;
import edu.hvrigazov.parallel.run.Utils;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class DummyTest {

    @Test
    public void dummy() {
        assertNotNull(new App());
        assertNotNull(new App.Commands());
        assertNotNull(new Options());
        assertNotNull(new Utils());
    }
}
