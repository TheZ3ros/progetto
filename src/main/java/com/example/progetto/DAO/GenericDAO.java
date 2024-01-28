package com.example.progetto.DAO;


import java.sql.SQLException;

public interface GenericDAO<P> {

    P execute(Object... params) throws SQLException;

}