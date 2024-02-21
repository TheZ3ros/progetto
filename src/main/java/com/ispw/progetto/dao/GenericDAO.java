package com.ispw.progetto.dao;


import com.ispw.progetto.exception.NotValidCouponException;

import java.sql.SQLException;

public interface GenericDAO<P> {

    P execute(Object... params) throws SQLException, NotValidCouponException;

}