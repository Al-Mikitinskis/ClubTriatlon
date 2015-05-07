package es.udc.tfg_es.clubtriatlon.model;

/* ClubTriatlon: a web app to management of administrative work of a triathlon club
 Copyright (C) 2015 Alejandro Mikitinskis

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software Foundation,
 Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA

 Contact here: alejandro.mikitinskis@udc.es */

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.tfg_es.clubtriatlon.utils.dao.GenericDaoHibernate;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@Repository("TrainingDao")
public class TrainingDaoHibernate extends GenericDaoHibernate<Training, Long> implements
		TrainingDao {
	
	@SuppressWarnings("unchecked")
	public List<Training> getAllTrainings()
	{
		return getSession().createQuery("SELECT t FROM Training t").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Training> getActiveTrainings()
	{
		return getSession().createQuery(
				"SELECT t FROM Training t WHERE t.status = true ORDER BY t.name").list();
	}
	
	public Training getTrainingByName(String name) throws InstanceNotFoundException {
		Training training = (Training) getSession().createQuery(
    			"SELECT t FROM Training t WHERE t.name = :name")
    			.setParameter("name", name)
    			.uniqueResult();
    	if (training == null) {
   			throw new InstanceNotFoundException(name, Training.class.getName());
    	} else {
    		return training;
    	}
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Training> getAllTrainingsOrderByName()
//	{
//		return getSession().createQuery("SELECT t FROM Training t ORDER BY t.name").list();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Training> getAllTrainingsOrderByStatusActive()
//	{
//		return getSession().createQuery("SELECT t FROM Training t ORDER BY t.name").list();
//	}
	
}
