package com.ispw.progetto.pattern.factory;

import com.ispw.progetto.model.Buono;

public interface FactoryInterface {
     EntityFactory CreateEntity(int type);
     Buono CreateBuono(String codice, int valore);
}
