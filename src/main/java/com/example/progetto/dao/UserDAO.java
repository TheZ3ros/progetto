package com.example.progetto.dao;
import com.example.progetto.bean.SignUpUserBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.SQLStatementException;
import com.example.progetto.model.User;
import com.example.progetto.pattern.factory.EntityFactory;
import com.example.progetto.pattern.factory.Factory;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class UserDAO implements GenericDAO <EntityFactory> {
    private final Connectivity connection;
    public UserDAO() throws SQLException, IOException {
        connection =Connectivity.getSingletonInstance();

    }

    @Override
    public EntityFactory execute(Object... params) throws SQLException {
        String username = (String) params[0];
        Factory factory=new Factory();
        EntityFactory utente=factory.CreateEntity(1);
        try(CallableStatement cs = connection.conn.prepareCall("{call GetPassword(?,?)}")) {
            cs.setString(1, username);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeQuery();
            utente.setPassword(cs.getString(2));
            utente.setUsername((String) params[0]);
        }

        return utente;
    }
    public void registrazione(User user) throws ExistsUserException, SQLException, SQLStatementException {
        checkUser(user);
        String username=user.getUsername();
        String password= user.getPassword();
        String nome=user.getNome();
        String cognome=user.getCognome();
        String email=user.getEmail();
        try(CallableStatement cs = connection.conn.prepareCall("{call SetUser(?,?,?,?,?)}")) {
            cs.setString(1, username);
            cs.setString(2, password);
            cs.setString(3, nome);
            cs.setString(4, cognome);
            cs.setString(5, email);

            cs.executeQuery();
        } catch (SQLException e) {
            throw new SQLStatementException("Errore nell'esecuzione dello statement SQL");
        }

    }

    public void checkUser(User user) throws ExistsUserException, SQLException {
        try (CallableStatement cs = connection.conn.prepareCall("{call CheckUser(?,?)}")){

            cs.setString(1, user.getUsername());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeQuery();
            int n = cs.getInt(2);
            if (n == 1) {
                throw new ExistsUserException("utente già esistente");
            }
        }
        catch (SQLException e){
            throw new SQLException("errore username"+e.getMessage());
        }


    }
    public SignUpUserBean info(String username) throws ExistsUserException, SQLException {
        try (CallableStatement cs = connection.conn.prepareCall("{call SearchUser(?,?,?,?)}")){

            cs.setString(1, username);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);

            cs.executeQuery();
            SignUpUserBean trueUser=new SignUpUserBean();
            String nome = cs.getString(2);
            String cognome = cs.getString(3);
            String email = cs.getString(4);
            trueUser.setUsername(username);
            trueUser.setEmail(email);
            trueUser.setCognome(cognome);
            trueUser.setNome(nome);
            return trueUser;
        }
        catch (SQLException e){
            throw new SQLException("errore username"+e.getMessage());
        }


    }
    }

