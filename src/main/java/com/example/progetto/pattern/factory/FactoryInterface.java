package com.example.progetto.pattern.factory;

import com.example.progetto.model.Buono;

public interface FactoryInterface {
     EntityFactory CreateEntity(int type);
     Buono CreateBuono(String codice, int valore);
}
