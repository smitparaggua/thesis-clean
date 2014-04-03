package com.csg.warrior.core;

import java.io.Serializable;

public class BladeKeyStatus implements Serializable {
    private final boolean isRegistered;
    private final boolean isKeyValid;

    public BladeKeyStatus(boolean isRegistered, boolean isKeyValid) {
        this.isRegistered = isRegistered;
        this.isKeyValid = isKeyValid;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public boolean isKeyValid() {
        return isKeyValid;
    }
}
