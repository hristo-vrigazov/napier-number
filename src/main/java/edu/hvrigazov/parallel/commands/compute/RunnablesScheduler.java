package edu.hvrigazov.parallel.commands.compute;

import edu.hvrigazov.parallel.parsing.ComputationSettings;
import edu.hvrigazov.parallel.parsing.ParsedOptions;
import edu.hvrigazov.parallel.run.NapierComputation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class RunnablesScheduler implements Supplier<List<NapierComputation>> {
    private List<NapierComputation> napierComputations;

    public RunnablesScheduler(ParsedOptions computationSettings) {
        this.napierComputations = new ArrayList<>();

        int intervalSize = (computationSettings.to() - computationSettings.from()) / computationSettings.tasks();

        for (int i = 0; i < computationSettings.tasks() - 1; i++) {
            boolean quiet = computationSettings.quiet();
            int from = i * intervalSize;
            int to = from + intervalSize;
            NapierComputation napierComputation = new NapierComputation(quiet, from, to);
            this.napierComputations.add(napierComputation);
        }

        boolean quiet = computationSettings.quiet();
        int from = (computationSettings.tasks() - 1) * intervalSize;
        int to = computationSettings.to();
        NapierComputation napierComputation = new NapierComputation(quiet, from, to);
        this.napierComputations.add(napierComputation);

    }

    @Override
    public List<NapierComputation> get() {
        return napierComputations;
    }
}
