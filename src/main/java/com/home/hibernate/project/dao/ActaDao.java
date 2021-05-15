package com.home.hibernate.project.dao;

import com.home.hibernate.project.dominio.Acta;

public class ActaDao extends AbstractDao<Acta>{

    public ActaDao() {
        settClass(Acta.class);
    }
}
