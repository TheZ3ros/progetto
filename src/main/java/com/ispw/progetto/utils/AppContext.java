package com.ispw.progetto.utils;

public class AppContext {
    private final boolean isGUI;
    private final PersistenceMode persistenceMode;

    public AppContext(boolean isGUI, PersistenceMode persistenceMode) {
        this.isGUI = isGUI;
        this.persistenceMode = persistenceMode;
    }

    public boolean isGUI() {
        return isGUI;
    }

    public PersistenceMode getPersistenceMode() {
        return persistenceMode;
    }
}

