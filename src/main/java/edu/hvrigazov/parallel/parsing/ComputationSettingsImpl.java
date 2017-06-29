package edu.hvrigazov.parallel.parsing;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class ComputationSettingsImpl implements ComputationSettings {

    private boolean quiet;
    private Integer to;
    private Integer tasks;
    private Integer from;

    public ComputationSettingsImpl(boolean quiet, Integer to, Integer tasks, Integer from) {
        this.quiet = quiet;
        this.to = to;
        this.tasks = tasks;
        this.from = from;
    }

    @Override
    public boolean quiet() {
        return quiet;
    }

    @Override
    public Integer to() {
        return to;
    }

    @Override
    public Integer tasks() {
        return tasks;
    }

    @Override
    public Integer from() {
        return from;
    }
}
