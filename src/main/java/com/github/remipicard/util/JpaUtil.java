package com.github.remipicard.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory emf = null;

	private JpaUtil() {
	}

	public static synchronized EntityManagerFactory getEmf() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("PROJET_JPA_HIBERNATE");
		}
		return emf;
	}

	public static synchronized void close() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

}
