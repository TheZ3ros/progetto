package com.ispw.progetto.utils;

public class AppContext {

    private static AppContext instance;

    private boolean isGui;
    private PersistenceMode persistenceMode;

    private AppContext() {}

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public boolean isGui() {
        return isGui;
    }

    public void setGui(boolean isGui) {
        this.isGui = isGui;
    }

    public PersistenceMode getPersistenceMode() {
        return persistenceMode;
    }

    public void setPersistenceMode(PersistenceMode persistenceMode) {
        this.persistenceMode = persistenceMode;
    }
}
