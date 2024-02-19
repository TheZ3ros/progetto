package com.example.progetto.controller_app;

import com.example.progetto.bean.SignUpUserBean;
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
    private BeanFactory currentUser;
    private SignUpUserBean user;

    public RegLoginControllerApp(BeanFactory user){

        currentUser=user;
    }
    public RegLoginControllerApp(SignUpUserBean user){
        this.user=user;
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
        userVero.setUser(username);
        userVero.setPassword(password);
        userVero.setNome(nome);
        userVero.setEmail(email);
        userVero.setCognome(cognome);
        userDAO.registrazione(userVero);

    }
    public SignUpUserBean info() throws SQLException, IOException, ExistsUserException {
        UserDAO userDAO=new UserDAO();
        SignUpUserBean userr=userDAO.info(currentUser);
        return userr;

    }
    }


