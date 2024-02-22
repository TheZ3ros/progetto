package com.ispw.progetto.pattern.factory;

import com.ispw.progetto.model.Agency;
import com.ispw.progetto.model.Buono;
import com.ispw.progetto.model.User;


public class Factory implements  FactoryInterface{


    @Override
    public EntityFactory createEntity(int type) {
        return switch (type) {
            case 1 -> new User();
            case 2 -> new Agency();
            default -> {
                try {
                    throw new Exception("Invalid type : " + type);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Override
    public Buono createBuono(String codice, int valore) {
        return new Buono(codice,valore);
    }

}
