package com.example.progetto.controller_app;

import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.dao.AgencyDAO;
import com.example.progetto.dao.UserDAO;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.PasswordIllegalException;
import com.example.progetto.exception.SQLStatementException;
import com.example.progetto.model.User;
import com.example.progetto.model.Agency;
import com.example.progetto.pattern.factory.BeanFactory;

import java.io.IOException;
import java.sql.SQLException;

public class RegLoginControllerApp {
    private final BeanFactory currentUser;

    public RegLoginControllerApp(BeanFactory user){

        currentUser=user;
    }
    public void loginUtente() throws SQLException, IOException, CredentialErrorException {
        UserDAO dao;
        dao=new UserDAO();
        User utente;
            utente= dao.execute(currentUser.getUsername());
        if (!currentUser.getPassword().equals(utente.getPassword())){
            throw new CredentialErrorException("credenziali errate");
        }

    }

    public void loginAgenzia() throws SQLException, IOException, CredentialErrorException {
        AgencyDAO dao;
        dao= new AgencyDAO();
        Agency agenzia;
            agenzia = dao.execute(currentUser.getUsername());
        if (!currentUser.getPassword().equals(agenzia.getPassword()))
            throw new CredentialErrorException("credenziali errate");
        }
public void registrazione() throws PasswordIllegalException, SQLException, IOException, ExistsUserException, SQLStatementException {
        String username;
        String password;
        username=currentUser.getPassword();
        password=currentUser.getUsername();
        if (password.length()<8){
            throw new PasswordIllegalException("password non valida, inserire almeno 8 caratteri");
        }
        UserDAO userDAO;
        userDAO=new UserDAO();
        User userVero;
        userVero=new User();
        userVero.setUser(username);
        userVero.setPassword(password);
        userDAO.registrazione(userVero);

    }
    }


