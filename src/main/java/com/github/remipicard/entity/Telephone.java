package com.github.remipicard.entity;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "telephone")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Telephone.findAll", query = "SELECT t FROM Telephone t"),
		@NamedQuery(name = "Telephone.findById", query = "SELECT t FROM Telephone t WHERE t.id = :id"),
		@NamedQuery(name = "Telephone.findByLibelle", query = "SELECT t FROM Telephone t WHERE t.libelle = :libelle"),
		@NamedQuery(name = "Telephone.findByNumero", query = "SELECT t FROM Telephone t WHERE t.numero = :numero") })
// @Cacheable
public class Telephone implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "libelle")
	private String libelle;
	@Basic(optional = false)
	@Column(name = "numero")
	private String numero;
	@JoinColumn(name = "id_personne", referencedColumnName = "id", nullable = false)
	@ManyToOne
	private Personne idPersonne;

	public Telephone() {
	}

	public Telephone(Integer id) {
		this.id = id;
	}

	public Telephone(Integer id, String numero) {
		this.id = id;
		this.numero = numero;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Personne getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Personne idPersonne) {
		this.idPersonne = idPersonne;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Telephone)) {
			return false;
		}
		Telephone other = (Telephone) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.github.remipicard.entity.Telephone[ id=" + id + " ]";
	}

}
