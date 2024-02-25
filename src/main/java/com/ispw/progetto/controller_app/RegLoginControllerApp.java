package com.ispw.progetto.controller_app;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.SignUpUserBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.exception.CredentialErrorException;
import com.ispw.progetto.dao.AgencyDAO;
import com.ispw.progetto.dao.UserDAO;
import com.ispw.progetto.exception.ExistsUserException;
import com.ispw.progetto.exception.PasswordIllegalException;
import com.ispw.progetto.exception.SQLStatementException;
import com.ispw.progetto.model.User;
import com.ispw.progetto.pattern.factory.EntityFactory;

import java.io.IOException;
import java.sql.SQLException;

public class RegLoginControllerApp {
    private UserBean currentUser;
    private SignUpUserBean user;
    private AgencyBean agency;

    public RegLoginControllerApp(UserBean user){

        currentUser=user;
    }
    public RegLoginControllerApp(SignUpUserBean user){
        this.user=user;
    }
    public RegLoginControllerApp(AgencyBean agency){
        this.agency=agency;
    }
    public void loginUtente() throws SQLException, IOException, CredentialErrorException {
        UserDAO dao;
        dao=new UserDAO();
        EntityFactory utente;
            utente= dao.execute(currentUser.getUsername());
        if (!currentUser.getPassword().equals(utente.getPassword())){
            throw new CredentialErrorException("credenziali errate");
        }

    }

    public void loginAgenzia() throws SQLException, IOException, CredentialErrorException {
        AgencyDAO dao;
        dao= new AgencyDAO();
        EntityFactory agenzia;
            agenzia = dao.execute(agency.getUsername());
        if (!agency.getPassword().equals(agenzia.getPassword()))
            throw new CredentialErrorException("credenziali errate");
        }
public void registrazione() throws PasswordIllegalException, SQLException, IOException, ExistsUserException, SQLStatementException {
        String username;
        String password;
        String nome;
        String cognome;
        String email;
        username=user.getUsername();
        password=user.getPassword();
        nome=user.getNome();
        cognome=user.getCognome();
        email=user.getEmail();

        if (password.length()<8){
            throw new PasswordIllegalException("password non valida, inserire almeno 8 caratteri");
        }
        UserDAO userDAO;
        userDAO=new UserDAO();
        User userVero;
        userVero=new User();
        userVero.setUsername(username);
        userVero.setPassword(password);
        userVero.setNome(nome);
        userVero.setEmail(email);
        userVero.setCognome(cognome);
        userDAO.registrazione(userVero);

    }
    public SignUpUserBean info() throws SQLException, IOException, ExistsUserException {
        UserDAO userDAO=new UserDAO();
        return userDAO.info(currentUser.getUsername());

    }
    }


