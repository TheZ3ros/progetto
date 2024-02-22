package com.ispw.progetto.pattern.factory;

import com.ispw.progetto.model.Buono;

public interface FactoryInterface {
     EntityFactory createEntity(int type);
     Buono createBuono(String codice, int valore);
}
