package com.home.hibernate.project;

import com.home.hibernate.project.dao.ReunionDao;

public class AppReuniones {
	public static void main(String[] args) {

		ReunionDao reunionDao = new ReunionDao();
		System.out.println("Reuniones de Pedro: " + reunionDao.reunionsParticipated("E002"));
	}
}
