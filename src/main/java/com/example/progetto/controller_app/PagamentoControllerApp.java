package com.example.progetto.controller_app;

import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.bean.BuonoBean;
import com.example.progetto.dao.BuonoDAO;
import com.example.progetto.model.Buono;

import java.io.IOException;
import java.sql.SQLException;

public class PagamentoControllerApp {
    public BuonoBean checkBuono(BuonoBean buonobean) throws SQLException, IOException, NotValidCouponException {
        BuonoDAO buonoDAO=new BuonoDAO();
        Buono buono;
        try{
            buono=buonoDAO.execute(buonobean);
        }
        catch(NotValidCouponException e){
            throw new NotValidCouponException(e.getMessage());
        }
        buonobean.setValore(buono.getCifra());
        return buonobean;

    }
}
