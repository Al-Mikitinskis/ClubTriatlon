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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.Training;
import es.udc.tfg_es.clubtriatlon.model.TrainingDao;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@Service("trainingService")
@Transactional
public class TrainingServiceImpl implements TrainingService {
	
	@Autowired
	private TrainingDao	trainingDao;
	
	public void save(Training training) {
		trainingDao.save(training);
	}
	
	public Training getTrainingById(Long trainingId) throws InstanceNotFoundException {
		return trainingDao.getTrainingById(trainingId);
	}
	
}
