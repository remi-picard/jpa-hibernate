package com.github.remipicard.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.github.remipicard.entity.Adresse;
import com.github.remipicard.entity.Personne;
import com.github.remipicard.entity.PersonneAdresse;
import com.github.remipicard.entity.Personne_;
import com.github.remipicard.entity.Telephone;
import com.github.remipicard.entity.Telephone_;
import com.github.remipicard.util.JpaUtil;

/**
 * Cette classe de démonstration illustre les concepts JPA et les exceptions
 * courantes auquelles le développeur est souvent confronté.
 * 
 * Dans la méthode main, décommenter la bonne méthode suivant le concept à
 * tester.
 * 
 * @author rpicard
 * 
 * @see https://www.editions-eni.fr/livre/jpa-et-java-hibernate-apprenez-le-mapping-objet-relationnel-orm-avec-java-9782409005824
 */
public class Demo {

	public static void main(String[] args) {

		System.out.println("============= Chargement du modèle de données =============");

		executeInTransaction((EntityManager em) -> {
			System.out.println("============= La démo commence ici =============");
			/**
			 * Modifications / Suppression
			 */
			savePhones(em);
			// suppression(em);

			/**
			 * Contexte de persistence / Objets détachés
			 */
			// detachedObject(em);
			// detach(em);
			// merge(em);

			/**
			 * Cache L1 / Flush / Refresh
			 */
			// cache(em);
			// refresh(em);
			// flush(em);

			/**
			 * API Criteria
			 */
			// multipleFetchs(em);

			/**
			 * Objet/Valeur transient
			 */
			// transientValue(em);
			// transientEntity(em);
		});

		// execute((EntityManagerFactory emf) -> {
		// System.out.println("============= La démo commence ici =============");
		// /**
		// * Lazyloading
		// */
		// lazyLoading(emf);
		//
		// /**
		// * Objets détachés (suite)
		// */
		// detachedObjectMultipleTransaction(emf);
		// });

	}

	/**
	 * Suppression d'une relation
	 * 
	 * Il ne faut pas UNIQUEMENT supprimer la donnée via la méthode remove(), il
	 * faut penser à RETIRER l'entité à supprimer de l'autre entité de la relation.
	 * 
	 * @param em
	 */
	private static void suppression(EntityManager em) {
		// INSERTION
		Personne p = new Personne();
		p.setNom("TRANSACTION 1");
		p.setTelephoneList(new ArrayList<>());
		Telephone t = new Telephone();
		t.setNumero("+33 6 12 34 56 78");
		t.setIdPersonne(p);
		p.getTelephoneList().add(t);
		em.persist(p);
		em.persist(t);

		// SUPPRESSION
		Personne personne = em.find(Personne.class, p.getId());
		// personne.getTelephoneList().forEach(tel -> em.remove(tel));
		// FIXME personne.setTelephoneList(null);
		// em.persist(personne);

		// OU

		// Telephone telASuppr = personne.getTelephoneList().get(0);
		// em.remove(telASuppr);
		// FIXME personne.getTelephoneList().remove(telASuppr);
	}

	/**
	 * org.hibernate.LazyInitializationException: failed to lazily initialize a
	 * collection
	 * 
	 * @param emf
	 */
	private static void lazyLoading(EntityManagerFactory emf) {
		// INSERTION
		Personne p = new Personne();
		p.setNom("TRANSACTION 1");
		p.setTelephoneList(new ArrayList<>());
		Telephone t = new Telephone();
		t.setNumero("+33 6 12 34 56 78");
		t.setIdPersonne(p);
		p.getTelephoneList().add(t);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.persist(t);
		em.getTransaction().commit();
		em.close();

		// RÉCUPÉRATION
		em = emf.createEntityManager();
		em.getTransaction().begin();
		p = em.find(Personne.class, p.getId());
		// 2ème requête
		System.out.println(p.getTelephoneList());
		em.close();

		// Hors transaction => LazyInitializationException
		// System.out.println(p.getTelephoneList());

	}

	/**
	 * org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch
	 * multiple bags
	 * 
	 * @param em
	 */
	private static void multipleFetchs(EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Personne> q = cb.createQuery(Personne.class);

		Root<Personne> root = q.from(Personne.class);
		Fetch<Personne, Telephone> f = root.fetch(Personne_.telephoneList);
		// root.fetch(Personne_.telephoneList);
		root.join(Personne_.telephoneList);
		root.join(Personne_.telephoneList);
		root.join(Personne_.telephoneList);
		root.join(Personne_.telephoneList);
		root.join(Personne_.telephoneList);

		Join<Personne, Telephone> j = (Join<Personne, Telephone>) f;
		q.where(cb.equal(j.get(Telephone_.numero), "06"));

		em.createQuery(q).getResultList();
	}

	private static void flush(EntityManager em) {
		Personne p = new Personne();
		p.setNom("CREATION");
		System.out.println("1er persist (création) => INSERT");
		em.persist(p);

		p.setNom("MODIF 1");
		System.out.println("2ème persist (modification) => UPDATE ?");
		em.persist(p);

		p.setNom("MODIF 2");
		System.out.println("3ème persist (modification) => UPDATE ?");
		em.persist(p);

		System.out.println("Flush => UPDATE ?");
		em.flush();

		p.setNom("MODIF 3");
		System.out.println("4ème persist (modification) => UPDATE ?");
		em.persist(p);

		throw new RuntimeException("Exception => Rollback du flush ?");
	}

	private static void cache(EntityManager em) {
		Personne p = new Personne();
		p.setNom("TRANSACTION 1");
		em.persist(p);

		// Cache L1 => PAS DE SELECT
		p = em.find(Personne.class, p.getId());
	}

	private static void detach(EntityManager em) {
		Personne p = new Personne();
		p.setNom("TRANSACTION 1");
		em.persist(p);

		// Détache l'entité du cache L1
		em.detach(p);

		// SELECT
		p = em.find(Personne.class, p.getId());
	}

	private static void refresh(EntityManager em) {
		Personne p = new Personne();
		p.setNom("TRANSACTION 1");
		em.persist(p);

		// Force refresh => FORCE SELECT
		em.refresh(p);
	}

	/**
	 * org.hibernate.PropertyValueException: not-null property references a null or
	 * transient value
	 * 
	 * @param em
	 */
	private static void transientValue(EntityManager em) {
		Telephone t = new Telephone();
		em.persist(t);
	}

	/**
	 * org.hibernate.TransientPropertyValueException: Not-null property references a
	 * transient value - transient instance must be saved beforeQuery current
	 * operation
	 * 
	 * Attempting to save one or more entities that have a non-nullable association
	 * with an unsaved transient entity. The unsaved transient entity must be saved
	 * in an operation prior to saving these dependent entities.
	 * 
	 * @param em
	 */
	private static void transientEntity(EntityManager em) {
		Telephone t = new Telephone();
		t.setNumero("06");
		t.setIdPersonne(new Personne());

		em.persist(t);
	}

	private static void merge(EntityManager em) {
		Personne p = new Personne();
		p.setNom("AVEC TELEPHONES");
		p.setId(1);

		em.merge(p);
	}

	private static void detachedObject(EntityManager em) {
		Personne p = new Personne();
		p.setNom("AVEC TELEPHONES");
		p.setId(1);
		em.persist(p);
	}

	private static void detachedObjectMultipleTransaction(EntityManagerFactory emf) {
		// TRANSACTION 1
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Personne p = new Personne();
		p.setNom("TRANSACTION 1");

		em.persist(p);

		em.getTransaction().commit();
		em.close();

		// TRANSACTION 2
		em = emf.createEntityManager();
		em.getTransaction().begin();

		p.setNom("TRANSACTION 2");
		em.persist(p);

		em.getTransaction().commit();
	}

	/**
	 * Sauvegarde relation birectionnelle AVEC/SANS cascade
	 * 
	 * @param em
	 */
	private static void savePhones(EntityManager em) {
		// Création de Personne
		Personne p = new Personne();
		// Définition des infos de Personne
		p.setNom("AVEC TELEPHONES");

		p.setTelephoneList(new ArrayList<>());
		Telephone t = new Telephone();
		t.setNumero("+33 6 12 34 56 78");
		// Lien dans les 2 sens
		t.setIdPersonne(p);

		p.getTelephoneList().add(t);

		em.persist(p);
		// Nécessaire si pas de CASCADE
		em.persist(t);
	}

	private static void saveAddresses(EntityManager em) {
		// Création de Personne
		Personne p = new Personne();
		// Définition des infos de Personne
		p.setNom("AVEC ADRESSES");
		p.setPersonneAdresseList(new ArrayList<>());
		em.persist(p);

		Adresse a = new Adresse();
		a.setLibelle("Impasse Charles Trenet");
		a.setPersonneAdresseList(new ArrayList<>());
		em.persist(a);

		PersonneAdresse address = new PersonneAdresse(p.getId(), a.getId());
		address.setPrincipale(true);
		address.setAdresse(a);
		address.setDebut(new Date());

		p.getPersonneAdresseList().add(address);
		a.getPersonneAdresseList().add(address);

		// Cascade
		em.persist(p);
	}

	private static void execute(Consumer<EntityManagerFactory> treatment) {
		try {
			EntityManagerFactory emf = JpaUtil.getEmf();

			treatment.accept(emf);

		} finally {
			JpaUtil.close();
		}
	}

	private static void executeInTransaction(Consumer<EntityManager> treatment) {
		try {
			EntityManagerFactory emf = JpaUtil.getEmf();
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			treatment.accept(em);

			em.getTransaction().commit();

		} finally {
			JpaUtil.close();
		}
	}

}
