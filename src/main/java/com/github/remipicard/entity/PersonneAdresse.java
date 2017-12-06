package com.github.remipicard.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "personne_adresse")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "PersonneAdresse.findAll", query = "SELECT p FROM PersonneAdresse p"),
		@NamedQuery(name = "PersonneAdresse.findByIdAdresse", query = "SELECT p FROM PersonneAdresse p WHERE p.personneAdressePK.idAdresse = :idAdresse"),
		@NamedQuery(name = "PersonneAdresse.findByIdPersonne", query = "SELECT p FROM PersonneAdresse p WHERE p.personneAdressePK.idPersonne = :idPersonne"),
		@NamedQuery(name = "PersonneAdresse.findByPrincipale", query = "SELECT p FROM PersonneAdresse p WHERE p.principale = :principale"),
		@NamedQuery(name = "PersonneAdresse.findByDebut", query = "SELECT p FROM PersonneAdresse p WHERE p.debut = :debut"),
		@NamedQuery(name = "PersonneAdresse.findByFin", query = "SELECT p FROM PersonneAdresse p WHERE p.fin = :fin") })
public class PersonneAdresse implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PersonneAdressePK personneAdressePK;
	@Basic(optional = false)
	@Column(name = "principale")
	private boolean principale;
	@Basic(optional = false)
	@Column(name = "debut")
	@Temporal(TemporalType.DATE)
	private Date debut;
	@Column(name = "fin")
	@Temporal(TemporalType.DATE)
	private Date fin;
	@JoinColumn(name = "id_adresse", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Adresse adresse;
	@JoinColumn(name = "id_personne", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Personne personne;

	public PersonneAdresse() {
	}

	public PersonneAdresse(PersonneAdressePK personneAdressePK) {
		this.personneAdressePK = personneAdressePK;
	}

	public PersonneAdresse(PersonneAdressePK personneAdressePK, boolean principale, Date debut) {
		this.personneAdressePK = personneAdressePK;
		this.principale = principale;
		this.debut = debut;
	}

	public PersonneAdresse(int idAdresse, int idPersonne) {
		this.personneAdressePK = new PersonneAdressePK(idAdresse, idPersonne);
	}

	public PersonneAdressePK getPersonneAdressePK() {
		return personneAdressePK;
	}

	public void setPersonneAdressePK(PersonneAdressePK personneAdressePK) {
		this.personneAdressePK = personneAdressePK;
	}

	public boolean getPrincipale() {
		return principale;
	}

	public void setPrincipale(boolean principale) {
		this.principale = principale;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (personneAdressePK != null ? personneAdressePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof PersonneAdresse)) {
			return false;
		}
		PersonneAdresse other = (PersonneAdresse) object;
		if ((this.personneAdressePK == null && other.personneAdressePK != null)
				|| (this.personneAdressePK != null && !this.personneAdressePK.equals(other.personneAdressePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.github.remipicard.entity.PersonneAdresse[ personneAdressePK=" + personneAdressePK + " ]";
	}

}
