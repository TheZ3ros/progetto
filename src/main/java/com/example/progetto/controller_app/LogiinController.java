package com.example.progetto.controller_app;

import com.example.progetto.dao.AgencyDAO;
import com.example.progetto.dao.UserDAO;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.entity.User;
import com.example.progetto.entity.Agency;

import java.sql.SQLException;

public class LogiinController {
    private UserBean utentebean;
    private AgencyBean agencyBean;

    public LogiinController(UserBean user){

        utentebean=user;
    }
    public LogiinController(AgencyBean agency){

        agencyBean=agency;
    }
    public void login_utente(){
        UserDAO dao=new UserDAO();
        User utente;
        try {
            utente= dao.execute(utentebean.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (utentebean.getPassword().equals(utente.getPassword())){
            utentebean.setToken();
        }

    }

    public void login_agenzia(){
        AgencyDAO dao = new AgencyDAO();
        Agency agenzia;
        try {
            agenzia = dao.execute(agencyBean.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (agencyBean.getPassword().equals(agenzia.getPassword())){
            agencyBean.setToken();
        }

    }

}
