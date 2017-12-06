package com.github.remipicard.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "adresse")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
		@NamedQuery(name = "Adresse.findById", query = "SELECT a FROM Adresse a WHERE a.id = :id"),
		@NamedQuery(name = "Adresse.findByLibelle", query = "SELECT a FROM Adresse a WHERE a.libelle = :libelle") })
public class Adresse implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "libelle")
	private String libelle;
	@JoinColumn(name = "id_ville", referencedColumnName = "id")
	@ManyToOne
	private Ville idVille;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adresse")
	private List<PersonneAdresse> personneAdresseList;

	public Adresse() {
	}

	public Adresse(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Ville getIdVille() {
		return idVille;
	}

	public void setIdVille(Ville idVille) {
		this.idVille = idVille;
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
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Adresse)) {
			return false;
		}
		Adresse other = (Adresse) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.github.remipicard.entity.Adresse[ id=" + id + " ]";
	}

}
