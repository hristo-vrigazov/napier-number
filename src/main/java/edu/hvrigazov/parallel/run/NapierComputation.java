package edu.hvrigazov.parallel.run;

import edu.hvrigazov.parallel.parsing.ComputationSettings;
import org.apfloat.ApintMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class NapierComputation implements NapierComputationRunnable {

    private ComputationSettings computationSettings;

    private NapierComputationResult napierComputationResult;

    public NapierComputation(ComputationSettings computationSettings) {
        this.computationSettings = computationSettings;
    }

    @Override
    public void run() {
        BigDecimal e = BigDecimal.ZERO;
        BigDecimal one = new BigDecimal(1.0);
        BigDecimal two = new BigDecimal(2.0);

        long startTime = Calendar.getInstance().getTimeInMillis();

        for (int k = computationSettings.from(); k < computationSettings.to(); k++) {
            System.out.println(k);
            BigDecimal kDecimal = new BigDecimal(k);
            BigDecimal denominator =  Utils.factorial(2 * k);
            e = e.add(two.multiply(kDecimal).add(one).divide(denominator,  MathContext.DECIMAL128));
        }

        long executionTime = Calendar.getInstance().getTimeInMillis() - startTime;
        System.out.println("Elapsed: " + String.valueOf((executionTime / 1.0e3) + " s"));
        System.out.println(e);

        this.napierComputationResult = new NapierComputationResult(
                computationSettings.tasks(),
                executionTime,
                e
        );
    }

    @Override
    public NapierComputationResult getResult() {
        return napierComputationResult;
    }
}
