package com.home.hibernate.project;

import com.home.hibernate.project.dao.ActaDao;
import com.home.hibernate.project.dao.ReunionDao;
import com.home.hibernate.project.dao.SalaDao;
import com.home.hibernate.project.dominio.Acta;
import com.home.hibernate.project.dominio.Persona;
import com.home.hibernate.project.dominio.Reunion;
import com.home.hibernate.project.dominio.Sala;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class App {

    public static void main(String[] args) {

		ReunionDao reunionDao = new ReunionDao();
		ActaDao actaDao = new ActaDao();
		SalaDao salaDao = new SalaDao();

		Sala sala123 = new Sala("S123", "Trastero", 1);
		Sala sala101 = new Sala("S101", "Reunion primera planta", 10);
		Sala sala211 = new Sala("S211", "Prime hub", 25);
		Sala sala122 = new Sala("S122", "Dimensional hub", 50);

		salaDao.save(sala123);
		salaDao.save(sala101);
		salaDao.save(sala211);
		salaDao.save(sala122);

		Persona jack = new Persona("E001", "Jack", "Stranger");
		Persona aleor = new Persona("E002", "Aleor", "Dendrays");
		Persona kario = new Persona("E003", "Kario", "Kindred");
		Persona eleonora = new Persona("E004", "Eleonora", "Prime");

		Reunion r0 = new Reunion(LocalDateTime.now(), "Reunion de Test");
		Reunion r1 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.HOURS), "Reunion de Test");
		Reunion r2 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Next reunion");
		Reunion r3 = new Reunion(LocalDateTime.now().plus(1, ChronoUnit.DAYS), "Tomorrow reunion");
		Reunion r4 = new Reunion(LocalDateTime.now().minus(1, ChronoUnit.DAYS), "Yesterday reunion");

		r0.addParticipantes(jack);
		r0.setSala(sala123);
		reunionDao.save(r0);
		Acta a0 = new Acta("Jack has to provide new methods to speak with Sdjanoor's", r0);
		actaDao.save(a0);
		reunionDao.update(r0);

		r1.addParticipantes(jack);
		r1.addParticipantes(aleor);
		r1.addParticipantes(kario);
		r1.addParticipantes(eleonora);
		r1.setSala(sala101);
		reunionDao.save(r1);

		r2.addParticipantes(aleor);
		r2.addParticipantes(kario);
		r2.setSala(sala211);
		reunionDao.save(r2);

		r3.addParticipantes(jack);
		r3.addParticipantes(eleonora);
		r3.setSala(sala211);
		reunionDao.save(r3);

		r4.addParticipantes(jack);
		r4.addParticipantes(kario);
		r4.addParticipantes(eleonora);
		r4.addParticipantes(aleor);
		r4.setSala(sala122);
		reunionDao.save(r4);

		Acta a4 = new Acta("Jack S., Aleor D., Kario K., Eleonora P. has to manage new anger on the side of intergalactic transfers", r4);
		actaDao.save(a4);
		reunionDao.update(r4);

		List<Reunion> reunions = reunionDao.getAll();
		System.out.println("All the reunions");

		try {
			System.out.println("Next reunion is: " + reunionDao.proximaReunion());
		} catch (NoResultException e) {
			System.out.println("There is no reunions");
		}
		List<Reunion> reunionsTomorrow = reunionDao.reunionesManyana();
		System.out.println("For tomorrow:" + reunionsTomorrow);
	}
}
