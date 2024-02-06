package com.example.progetto.dao;


import java.sql.SQLException;

public interface GenericDAO<P> {

    P execute(Object... params) throws SQLException;

}