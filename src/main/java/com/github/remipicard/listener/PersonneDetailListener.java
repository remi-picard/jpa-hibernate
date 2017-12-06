package com.github.remipicard.listener;

import javax.persistence.PrePersist;

import com.github.remipicard.entity.PersonneDetail;

public class PersonneDetailListener {

	@PrePersist
	public void avantSauvegarde(PersonneDetail p) {
		p.setIdPersonne(p.getPersonne().getId());
	}

}
