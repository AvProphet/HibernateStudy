package com.home.hibernate.project.dao;

import com.home.hibernate.project.dominio.Persona;

public class PersonaDao extends AbstractDao<Persona> {

    public PersonaDao() {
        settClass(Persona.class);
    }
}
