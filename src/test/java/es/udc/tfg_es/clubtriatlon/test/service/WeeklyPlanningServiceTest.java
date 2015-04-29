package es.udc.tfg_es.clubtriatlon.test.service;

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

import static es.udc.tfg_es.clubtriatlon.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static es.udc.tfg_es.clubtriatlon.utils.GlobalNames.SPRING_CONFIG_FILE;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.service.WeeklyPlanningService;
import es.udc.tfg_es.clubtriatlon.model.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class WeeklyPlanningServiceTest {
	
	@Autowired
	private WeeklyPlanningService	weeklyPlanningService;
	
	@Test
	public void testFindWeeklyPlannings() throws InstanceNotFoundException {
		
		// Training myTraining = new Training("myTraining");
		// Planning myPlanning = new Planning(myTraining, "2015/03/25",
		// "myPDF".getBytes());
		List<Planning> list = new ArrayList();
		// list.add(Planning);
		
		WeeklyPlanning wp1 = new WeeklyPlanning("2015 - s.1", list);
		WeeklyPlanning wp2 = new WeeklyPlanning("2015 - s.2", list);
		WeeklyPlanning wp3 = new WeeklyPlanning("2015 - s.3", list);
		WeeklyPlanning wp4 = new WeeklyPlanning("2015 - s.4", list);
		WeeklyPlanning wp5 = new WeeklyPlanning("2015 - s.5", list);
		WeeklyPlanning wp6 = new WeeklyPlanning("2015 - s.6", list);
		WeeklyPlanning wp7 = new WeeklyPlanning("2015 - s.7", list);
		
		// Get the 5 weekly plannings (start index, count elements)
		List<WeeklyPlanning> listFounds = weeklyPlanningService.findWeeklyPlannings(0, 5);
		assertTrue(listFounds.size() == 5);
		// Must be order desc
		assertEquals(wp7, listFounds.get(0));
		assertEquals(wp6, listFounds.get(1));
		assertEquals(wp5, listFounds.get(2));
		assertEquals(wp4, listFounds.get(3));
		assertEquals(wp3, listFounds.get(4));
		
		// Get the next 5 weekly plannings
		listFounds = weeklyPlanningService.findWeeklyPlannings(5, 5);
		assertTrue(listFounds.size() == 2);
		assertEquals(wp2, listFounds.get(0));
		assertEquals(wp1, listFounds.get(1));
		
	}
	
}
