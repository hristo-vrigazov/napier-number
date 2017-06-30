package edu.hvrigazov.parallel.parsing;

/**
 * Created by hvrigazov on 29.06.17.
 */
public class ComputationSettingsImpl implements ComputationSettings {

    private boolean quiet;
    private Integer to;
    private Integer from;

    public ComputationSettingsImpl(boolean quiet, Integer from, Integer to) {
        this.quiet = quiet;
        this.to = to;
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
    public Integer from() {
        return from;
    }
}
