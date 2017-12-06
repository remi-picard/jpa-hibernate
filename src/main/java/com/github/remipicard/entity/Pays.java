package com.github.remipicard.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "pays")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p"),
		@NamedQuery(name = "Pays.findById", query = "SELECT p FROM Pays p WHERE p.id = :id"),
		@NamedQuery(name = "Pays.findByMonnai", query = "SELECT p FROM Pays p WHERE p.monnai = :monnai"),
		@NamedQuery(name = "Pays.findByNom", query = "SELECT p FROM Pays p WHERE p.nom = :nom") })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Pays implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "monnai")
	private String monnai;
	@Basic(optional = false)
	@Column(name = "nom")
	private String nom;
	@Basic
	@Column(name = "population")
	private BigInteger population;
	@Basic
	@Column(name = "continent")
	private String continent;
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@ManyToMany(mappedBy = "paysList")
	private List<Langue> langueList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pays")
	private List<Ville> villeList;

	public Pays() {
	}

	public Pays(Integer id) {
		this.id = id;
	}

	public Pays(Integer id, String monnai, String nom) {
		this.id = id;
		this.monnai = monnai;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonnai() {
		return monnai;
	}

	public void setMonnai(String monnai) {
		this.monnai = monnai;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigInteger getPopulation() {
		return population;
	}

	public void setPopulation(BigInteger population) {
		this.population = population;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	@XmlTransient
	public List<Langue> getLangueList() {
		return langueList;
	}

	public void setLangueList(List<Langue> langueList) {
		this.langueList = langueList;
	}

	@XmlTransient
	public List<Ville> getVilleList() {
		return villeList;
	}

	public void setVilleList(List<Ville> villeList) {
		this.villeList = villeList;
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
		if (!(object instanceof Pays)) {
			return false;
		}
		Pays other = (Pays) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.github.remipicard.entity.Pays[ id=" + id + " ]";
	}

}
