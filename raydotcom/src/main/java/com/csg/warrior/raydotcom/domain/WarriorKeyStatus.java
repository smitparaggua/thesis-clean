package com.csg.warrior.raydotcom.domain;

public class WarriorKeyStatus {
    private boolean isRegisteredInWarrior;
    private boolean isWarriorKeyValid;

    public WarriorKeyStatus(boolean isRegisteredInWarrior, boolean isWarriorKeyValid) {
        this.isRegisteredInWarrior = isRegisteredInWarrior;
        this.isWarriorKeyValid = isWarriorKeyValid;
    }

    public boolean isRegisteredInWarrior() {
        return isRegisteredInWarrior;
    }

    public void setRegisteredInWarrior(boolean registeredInWarrior) {
        isRegisteredInWarrior = registeredInWarrior;
    }

    public boolean isWarriorKeyValid() {
        return isWarriorKeyValid;
    }

    public void setWarriorKeyValid(boolean warriorKeyValid) {
        isWarriorKeyValid = warriorKeyValid;
    }
}
