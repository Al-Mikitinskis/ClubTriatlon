package es.udc.tfg_es.clubtriatlon.utils.dao;
/* BSD License */

import java.io.Serializable;

import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

public interface GenericDao <E, PK extends Serializable>{

	void save(E entity);

	E find(PK id) throws InstanceNotFoundException;

	boolean exists(PK id);

	void remove(PK id) throws InstanceNotFoundException;

}
