package edu.hvrigazov.parallel.run;

import edu.hvrigazov.parallel.parsing.ComputationSettings;
import edu.hvrigazov.parallel.suppliers.NapierComputationResultSupplier;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class SingleNapierComputation implements NapierComputation {

    private ComputationSettings computationSettings;
    private NapierComputationResultSupplier napierComputationResultSupplier;

    public SingleNapierComputation(ComputationSettings computationSettings,
                                   NapierComputationResultSupplier napierComputationResultSupplier) {
        this.computationSettings = computationSettings;
        this.napierComputationResultSupplier = napierComputationResultSupplier;
        System.out.println("From " + computationSettings.from() + " to " + computationSettings.to()
                + ", quiet: " + computationSettings.quiet());
    }

    @Override
    public NapierComputationResult call() throws Exception {
        BigDecimal e = BigDecimal.ZERO;
        BigDecimal one = new BigDecimal(1.0);
        BigDecimal two = new BigDecimal(2.0);

        long startTime = Calendar.getInstance().getTimeInMillis();

        for (int k = computationSettings.from(); k < computationSettings.to(); k++) {
            BigDecimal kDecimal = new BigDecimal(k);
            BigDecimal denominator =  Utils.factorial(2 * k);
            e = e.add(two.multiply(kDecimal).add(one).divide(denominator,  MathContext.DECIMAL128));
        }

        long executionTime = Calendar.getInstance().getTimeInMillis() - startTime;

        return napierComputationResultSupplier.get(executionTime, e);
    }
}
