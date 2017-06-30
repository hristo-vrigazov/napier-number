package edu.hvrigazov.parallel.run;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class UtilsTest {

    @Test
    public void factorialTestZero() {
        assertEquals(BigDecimal.ONE, Utils.factorial(0));
    }

    @Test
    public void factorialTwelve() {
        assertEquals(new BigDecimal(479001600), Utils.factorial(12));
    }
}
