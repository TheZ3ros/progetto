package com.example.progetto.DAO;

import com.example.progetto.entity.Trip;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<P> {

    P execute(Object... params) throws SQLException;

}