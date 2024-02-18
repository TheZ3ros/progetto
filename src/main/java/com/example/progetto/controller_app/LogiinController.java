package com.example.progetto.controller_app;

import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.dao.AgencyDAO;
import com.example.progetto.dao.UserDAO;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.model.User;
import com.example.progetto.model.Agency;

import java.io.IOException;
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
    public void loginUtente() throws SQLException, IOException, CredentialErrorException {
        UserDAO dao=new UserDAO();
        User utente;
            utente= dao.execute(utentebean.getUsername());
        if (utentebean.getPassword().equals(utente.getPassword())){
            utentebean.setToken();
        }
        else{
            throw new CredentialErrorException("credenziali errate");
        }

    }

    public void loginAgenzia() throws SQLException, IOException, CredentialErrorException {
        AgencyDAO dao = new AgencyDAO();
        Agency agenzia;
            agenzia = dao.execute(agencyBean.getUsername());
        if (!agencyBean.getPassword().equals(agenzia.getPassword()))
            throw new CredentialErrorException("credenziali errate");
        }

    }


