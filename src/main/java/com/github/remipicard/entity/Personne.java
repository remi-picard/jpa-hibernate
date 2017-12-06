package com.github.remipicard.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "personne")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
		@NamedQuery(name = "Personne.findById", query = "SELECT p FROM Personne p WHERE p.id = :id"),
		@NamedQuery(name = "Personne.findByDateNaissance", query = "SELECT p FROM Personne p WHERE p.dateNaissance = :dateNaissance"),
		@NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
		@NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom") })
public class Personne implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", columnDefinition = "serial")
	private Integer id;
	@Column(name = "date_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "personne")
	private PersonneDetail personneDetail;
	@OneToMany(/* cascade = CascadeType.ALL, */mappedBy = "idPersonne")
	private List<Telephone> telephoneList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personne")
	private List<PersonneAdresse> personneAdresseList;

	public Personne() {
	}

	public Personne(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public PersonneDetail getPersonneDetail() {
		return personneDetail;
	}

	public void setPersonneDetail(PersonneDetail personneDetail) {
		this.personneDetail = personneDetail;
	}

	@XmlTransient
	public List<Telephone> getTelephoneList() {
		return telephoneList;
	}

	public void setTelephoneList(List<Telephone> telephoneList) {
		this.telephoneList = telephoneList;
	}

	@XmlTransient
	public List<PersonneAdresse> getPersonneAdresseList() {
		return personneAdresseList;
	}

	public void setPersonneAdresseList(List<PersonneAdresse> personneAdresseList) {
		this.personneAdresseList = personneAdresseList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Personne)) {
			return false;
		}
		Personne other = (Personne) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.github.remipicard.entity.Personne[ id=" + id + " ]";
	}

}
