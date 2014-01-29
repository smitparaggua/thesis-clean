package com.csg.warrior.core;

import java.io.Serializable;

public class WarriorKeyStatus implements Serializable {
    private final boolean isRegisteredInWarrior;
    private final boolean isWarriorKeyValid;

    public WarriorKeyStatus(boolean isRegisteredInWarrior, boolean isWarriorKeyValid) {
        this.isRegisteredInWarrior = isRegisteredInWarrior;
        this.isWarriorKeyValid = isWarriorKeyValid;
    }

    public boolean isRegisteredInWarrior() {
        return isRegisteredInWarrior;
    }

    public boolean isWarriorKeyValid() {
        return isWarriorKeyValid;
    }
}
