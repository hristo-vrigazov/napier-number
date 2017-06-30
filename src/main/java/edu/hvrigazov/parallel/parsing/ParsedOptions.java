package edu.hvrigazov.parallel.parsing;

import java.util.Map;

import static edu.hvrigazov.parallel.parsing.Options.*;

/**
 * Created by hvrigazov on 28.06.17.
 */
public class ParsedOptions implements ComputationSettings, CompositeComputationSettings {

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

    public ParsedOptions(boolean quiet, int precision, int tasks, String output) {
        this.quiet = quiet;
        this.precision = precision;
        this.tasks = tasks;
        this.output = output;
    }

    @Override
    public boolean quiet() {
        return quiet;
    }

    @Override
    public Integer to() {
        return precision;
    }

    @Override
    public Integer tasks() {
        return tasks;
    }

    @Override
    public Integer from() {
        return 0;
    }

    @Override
    public String output() {
        return output;
    }

    public ParsedOptions withTasks(int tasks) {
        return new ParsedOptions(quiet, precision, tasks, output);
    }

    private <T> T get(Map<String, Object> opts, String option) {
        return (T) opts.get(option);
    }

}
