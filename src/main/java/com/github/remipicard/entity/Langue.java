package com.github.remipicard.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "langue")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Langue.findAll", query = "SELECT l FROM Langue l"),
		@NamedQuery(name = "Langue.findById", query = "SELECT l FROM Langue l WHERE l.id = :id"),
		@NamedQuery(name = "Langue.findByNom", query = "SELECT l FROM Langue l WHERE l.nom = :nom") })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Langue implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "nom")
	private String nom;
	@JoinTable(name = "pays_langue", joinColumns = {
			@JoinColumn(name = "id_langue", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_pays", referencedColumnName = "id") })
	@ManyToMany
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	private List<Pays> paysList;

	public Langue() {
	}

	public Langue(Integer id) {
		this.id = id;
	}

	public Langue(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@XmlTransient
	public List<Pays> getPaysList() {
		return paysList;
	}

	public void setPaysList(List<Pays> paysList) {
		this.paysList = paysList;
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
		if (!(object instanceof Langue)) {
			return false;
		}
		Langue other = (Langue) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.github.remipicard.entity.Langue[ id=" + id + " ]";
	}

}
