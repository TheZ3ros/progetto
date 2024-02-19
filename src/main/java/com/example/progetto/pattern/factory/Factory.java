package com.example.progetto.pattern.factory;

import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;

public class Factory{
    public BeanFactory createBean(int type) throws Exception{
        return switch (type) {
            case 1 -> new UserBean();
            case 2 -> new AgencyBean();
            default -> throw new Exception("Invalid type : " + type);
        };
    }



}
