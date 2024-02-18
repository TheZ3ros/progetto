package com.example.progetto.controller_app;

import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.dao.AgencyDAO;
import com.example.progetto.dao.UserDAO;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.exception.PasswordIllegalException;
import com.example.progetto.model.User;
import com.example.progetto.model.Agency;

import java.io.IOException;
import java.sql.SQLException;

public class RegLoginControllerApp {
    private UserBean utentebean;
    private AgencyBean agencyBean;

    public RegLoginControllerApp(UserBean user){

        utentebean=user;
    }
    public RegLoginControllerApp(AgencyBean agency){

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
public void registrazione() throws PasswordIllegalException, SQLException, IOException {
        String username= this.utentebean.getUsername();
        String password= this.utentebean.getPassword();
        if (password.length()<8){
            throw new PasswordIllegalException("password non valida, inserire almeno 8 caratteri");
        }
        UserDAO userDAO=new UserDAO();
        User userVero=new User();
        userVero.setUser(username);
        userVero.setPassword(password);
        userDAO.registrazione(userVero);

    }
    }


