package com.home.hibernate.project;

import com.home.hibernate.project.dao.PersonaDao;
import com.home.hibernate.project.dao.ReunionDao;
import com.home.hibernate.project.dao.SalaDao;
import com.home.hibernate.project.dominio.Persona;
import com.home.hibernate.project.dominio.Sala;

import java.util.List;
import java.util.Optional;

public class Queries {

    public static void main(String[] args) {
        SalaDao salaDao = new SalaDao();

        List<Sala> salasForFour = salaDao.findSalasParaNum(4);
        System.out.println("Salas for 4: " + salasForFour);

        List<Sala> salasForThree = salaDao.findSalasParaNumOld(3);
        System.out.println("Salas for 3" + salasForThree);

        List<Sala> salasForThreeNew = salaDao.findSalasParaNumUpdated(3);
        System.out.println("Salas for 3" + salasForThreeNew);

        PersonaDao personaDao = new PersonaDao();

        Optional<Persona> optionalPersona = new PersonaDao().get(1);
        if (optionalPersona.isPresent()) {
            Persona p = optionalPersona.get();
            System.out.println("Person " + p);
        }

        ReunionDao reunionDao= new ReunionDao();
        System.out.println("Reunion from: " +reunionDao.reunionsParticipated("E001"));
    }
}
