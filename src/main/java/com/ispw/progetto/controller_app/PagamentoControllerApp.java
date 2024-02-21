package com.ispw.progetto.controller_app;

import com.ispw.progetto.exception.CardNotTrueException;
import com.ispw.progetto.exception.NotValidCouponException;
import com.ispw.progetto.bean.BuonoBean;
import com.ispw.progetto.dao.BuonoDAO;
import com.ispw.progetto.model.Buono;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class PagamentoControllerApp {
    public BuonoBean checkBuono(BuonoBean buonobean) throws SQLException, IOException, NotValidCouponException {
        BuonoDAO buonoDAO;
        buonoDAO=new BuonoDAO();
        Buono buono;
        try{
            buono=buonoDAO.execute(buonobean.getCodice());
        }
        catch(NotValidCouponException e){
            throw new NotValidCouponException(e.getMessage());
        }
        buonobean.setValore(buono.getCifra());
        return buonobean;

    }
    public void checkCard(String numeroCarta, String cvvCode, LocalDate data) throws CardNotTrueException {
        if (numeroCarta.length() != 16 || cvvCode.length() != 3|| !(data.isAfter(LocalDate.now()))) {
            throw new CardNotTrueException("dati carta non validi");
        }

    }
}
