package com.example.progetto.dao;


import com.example.progetto.Exception.NotValidCouponException;

import java.sql.SQLException;

public interface GenericDAO<P> {

    P execute(Object... params) throws SQLException, NotValidCouponException;

}