package es.udc.tfg_es.clubtriatlon.service;

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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.Training;
import es.udc.tfg_es.clubtriatlon.model.TrainingDao;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@Service("trainingService")
@Transactional
public class TrainingServiceImpl implements TrainingService {
	
	@Autowired
	private TrainingDao	trainingDao;
	
	public void save(Training training) throws DuplicateInstanceException
	{
		try {
			trainingDao.getTrainingByName(training.getName());
			throw new DuplicateInstanceException(training.getName(), Training.class.getName());
		} catch (InstanceNotFoundException e) {
			trainingDao.save(training);
		}
	}
	
	public boolean remove(Long trainingId) throws InstanceNotFoundException
	{
		if (trainingDao.find(trainingId).isStatus()) {
			return false;
		} else {
			trainingDao.remove(trainingId);
			return true;
		}
	}
	
	public void changeName(Long trainingId, String name) throws InstanceNotFoundException
	{
		Training training = trainingDao.find(trainingId);
		training.setName(name);
	}
	
	public boolean changeStatus(Long trainingId) throws InstanceNotFoundException
	{
		Training training = trainingDao.find(trainingId);
		if (training.getUsers().size() > 0) {
			return false;
		} else {
			training.setStatus(!training.isStatus());
			return true;
		}
	}
	
	@Transactional(readOnly = true)
	public Training getTrainingById(Long trainingId) throws InstanceNotFoundException
	{
		return trainingDao.find(trainingId);
	}
	
	@Transactional(readOnly = true)
	public Training getTrainingByName(String name) throws InstanceNotFoundException
	{
		return trainingDao.getTrainingByName(name);
	}
	
	@Transactional(readOnly = true)
	public List<Training> getAllTrainings()
	{
		return trainingDao.getAllTrainings();
	}
	
	@Transactional(readOnly = true)
	public List<Training> getActiveTrainings()
	{
		return trainingDao.getActiveTrainings();
	}
	
	@Transactional(readOnly = true)
	public List<Training> orderById(List<Training> list)
	{
		Collections.sort(list, new Comparator<Training>() {
			public int compare(Training t1, Training t2)
			{
				return t1.getTrainingId().compareTo(t2.getTrainingId());
			}
		});
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<Training> orderByStatusActive(List<Training> list)
	{
		// return trainingDao.getAllTrainingsOrderByStatusActive();
		Collections.sort(list, new Comparator<Training>() {
			// First all true status training
			public int compare(Training t1, Training t2)
			{
				return Boolean.compare(t2.isStatus(), t1.isStatus());
			}
		});
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<Training> orderByName(List<Training> list)
	{
		// return trainingDao.getAllTrainingsOrderByName();
		Collections.sort(list, new Comparator<Training>() {
			// Order by name asc.
			public int compare(Training t1, Training t2)
			{
				return t1.getName().compareTo(t2.getName());
			}
		});
		return list;
	}
	
}
