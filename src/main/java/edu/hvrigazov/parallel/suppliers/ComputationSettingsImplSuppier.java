package edu.hvrigazov.parallel.suppliers;

import edu.hvrigazov.parallel.parsing.ComputationSettingsImpl;

/**
 * Created by hvrigazov on 30.06.17.
 */
public class ComputationSettingsImplSuppier {

    public ComputationSettingsImpl get(boolean quiet, Integer from, Integer to) {
        return new ComputationSettingsImpl(quiet, from, to);
    }

}
