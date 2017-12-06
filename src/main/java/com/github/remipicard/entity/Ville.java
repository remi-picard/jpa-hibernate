package com.github.remipicard.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ville")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v"),
		@NamedQuery(name = "Ville.findById", query = "SELECT v FROM Ville v WHERE v.id = :id"),
		@NamedQuery(name = "Ville.findByCapital", query = "SELECT v FROM Ville v WHERE v.capital = :capital"),
		@NamedQuery(name = "Ville.findByCodePostal", query = "SELECT v FROM Ville v WHERE v.codePostal = :codePostal"),
		@NamedQuery(name = "Ville.findByNom", query = "SELECT v FROM Ville v WHERE v.nom = :nom") })
public class Ville implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "capital")
	private boolean capital;
	@Column(name = "code_postal")
	private String codePostal;
	@Basic(optional = false)
	@Column(name = "nom")
	private String nom;
	@JoinColumn(name = "pays", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Pays pays;
	@OneToMany(mappedBy = "idVille")
	private List<Adresse> adresseList;

	public Ville() {
	}

	public Ville(Integer id) {
		this.id = id;
	}

	public Ville(Integer id, boolean capital, String nom) {
		this.id = id;
		this.capital = capital;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@XmlTransient
	public List<Adresse> getAdresseList() {
		return adresseList;
	}

	public void setAdresseList(List<Adresse> adresseList) {
		this.adresseList = adresseList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Ville)) {
			return false;
		}
		Ville other = (Ville) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.github.remipicard.entity.Ville[ id=" + id + " ]";
	}

}
