package edu.hvrigazov.parallel.parsing;

import edu.hvrigazov.parallel.run.NapierComputation;

import java.util.List;
import java.util.Map;

import static edu.hvrigazov.parallel.parsing.Options.*;

/**
 * Created by hvrigazov on 28.06.17.
 */
public class ParsedOptions implements ComputationSettings {

    private boolean quiet;
    private Integer precision;
    private Integer tasks;
    private String output;

    public ParsedOptions(Map<String, Object> opts) {
        this.quiet = this.<Boolean>get(opts, QUIET);
        this.precision = Integer.parseInt(this.get(opts, PRECISION));
        this.tasks = Integer.parseInt(this.get(opts, TASKS));
        this.output = this.get(opts, OUTPUT);
    }

    @Override
    public boolean quiet() {
        return quiet;
    }

    @Override
    public Integer to() {
        return precision;
    }

    public Integer tasks() {
        return tasks;
    }

    @Override
    public Integer from() {
        return 0;
    }

    public String output() {
        return output;
    }

    private <T> T get(Map<String, Object> opts, String option) {
        return (T) opts.get(option);
    }

}
