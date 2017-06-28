package edu.hvrigazov.parallel.parsing;

import java.util.Map;

import static edu.hvrigazov.parallel.parsing.Options.*;

/**
 * Created by hvrigazov on 28.06.17.
 */
public class ParsedOptions {
    private Map<String, Object> opts;

    public ParsedOptions(Map<String, Object> opts) {
        this.opts = opts;
    }

    public boolean help() {
        return this.<Boolean>get(HELP);
    }

    public boolean quiet() {
        return this.<Boolean>get(QUIET);
    }

    public boolean version() {
        return this.<Boolean>get(VERSION);
    }

    public Integer precision() {
        return Integer.parseInt(this.get(PRECISION));
    }

    public Integer tasks() {
        return Integer.parseInt(this.get(TASKS));
    }

    public String output() {
        return this.get(OUTPUT);
    }

    public <T> T get(String option) {
        return (T) opts.get(option);
    }
}
