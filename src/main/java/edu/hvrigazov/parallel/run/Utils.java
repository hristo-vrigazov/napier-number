package edu.hvrigazov.parallel.run;

import java.math.BigDecimal;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class Utils {

    public static BigDecimal factorial(int k) {
        BigDecimal product = BigDecimal.ONE;

        for (int i = 2; i <= k; i++) {
            product = product.multiply(new BigDecimal(i));
        }

        return product;
    }
}
