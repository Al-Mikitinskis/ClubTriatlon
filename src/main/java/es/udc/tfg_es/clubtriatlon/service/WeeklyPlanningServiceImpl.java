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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.Planning;
import es.udc.tfg_es.clubtriatlon.model.WeeklyPlanning;
import es.udc.tfg_es.clubtriatlon.model.WeeklyPlanningDao;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@Service("weeklyPlanningService")
@Transactional
public class WeeklyPlanningServiceImpl implements WeeklyPlanningService {
	
	@Autowired
	private WeeklyPlanningDao	weeklyPlanningDao;
	
	public void save(WeeklyPlanning weeklyPlanning) {
		weeklyPlanningDao.save(weeklyPlanning);
	}
	
	public WeeklyPlanning getWeeklyPlanningById(Long weeklyPlanningId)
			throws InstanceNotFoundException {
		return weeklyPlanningDao.getWeeklyPlanningById(weeklyPlanningId);
	}
	
	public List<WeeklyPlanning> findWeeklyPlannings(int startIndex, int count) {
		return weeklyPlanningDao.findWeeklyPlannings(startIndex, count);
	}
	
	public List<Planning> orderByTrainingAsc(WeeklyPlanning weeklyPlanning) {

		List<Planning> list = new ArrayList<Planning>();
		list.addAll(weeklyPlanning.getPlannings());
		Collections.sort(list, new Comparator<Planning>() {
		    public int compare(Planning p1, Planning p2) {		    	
		    	return p1.getTraining().getName().compareTo(p2.getTraining().getName());
		    }
		});
		return list;
		
	}
	
	public List<Planning> orderByTrainingDesc(WeeklyPlanning weeklyPlanning) {
		
//		List<Planning> list = orderByTrainingAsc(weeklyPlanning);
//		Collections.reverse(list);
//		return list;
		List<Planning> list = new ArrayList<Planning>();
		list.addAll(weeklyPlanning.getPlannings());
		Collections.sort(list, new Comparator<Planning>() {
		    public int compare(Planning p1, Planning p2) {		    	
		    	return p2.getTraining().getName().compareTo(p1.getTraining().getName());
		    }
		});
		return list;
		
	}
}
