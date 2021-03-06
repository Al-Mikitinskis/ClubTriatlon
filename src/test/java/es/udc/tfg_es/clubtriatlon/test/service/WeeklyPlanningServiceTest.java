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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.service.PlanningService;
import es.udc.tfg_es.clubtriatlon.service.UserService;
import es.udc.tfg_es.clubtriatlon.service.WeeklyPlanningService;
import es.udc.tfg_es.clubtriatlon.service.TrainingService;
import es.udc.tfg_es.clubtriatlon.utils.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.model.Planning;
import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.model.Training;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.WeeklyPlanning;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class WeeklyPlanningServiceTest {
	
	@Autowired
	private WeeklyPlanningService	weeklyPlanningService;
	
	@Autowired
	private PlanningService			planningService;
	
	@Autowired
	private TrainingService			trainingService;
	
	@Autowired
	private UserService				userService;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateWeeklyPlanning() throws ParseException, DuplicateInstanceException
	{
		Planning planning1 = new Planning();
		Planning planning2 = new Planning();
		Set<Planning> plannings = new HashSet<Planning>();
		plannings.add(planning1);
		plannings.add(planning2);
		
		WeeklyPlanning wp = new WeeklyPlanning("9", plannings);
		weeklyPlanningService.save(wp);
		
		Date date = new Date();
		assertTrue(wp.getCreationDate().getDay() == date.getDay()
				&& wp.getCreationDate().getMonth() == date.getMonth()
				&& wp.getCreationDate().getYear() == date.getYear());
		
		String wpName = date.getYear() + " - s.09";
		assertTrue(wp.getName().compareTo(wpName) == 0);
		
		assertTrue(wp.getPlannings().size() == 2 && wp.getPlannings().contains(planning1)
				&& wp.getPlannings().contains(planning2));
	}
	
	@Test
	public void testFindWeeklyPlannings() throws ParseException, DuplicateInstanceException
	{
		
		Set<Planning> list = new HashSet<Planning>();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("04/01/2015");
		Timestamp time = new Timestamp(date.getTime());
		WeeklyPlanning wp1 = new WeeklyPlanning("2015 - s.1", list, time);
		date = dateFormat.parse("11/01/2015");
		time = new Timestamp(date.getTime());
		WeeklyPlanning wp2 = new WeeklyPlanning("2015 - s.2", list, time);
		date = dateFormat.parse("18/01/2015");
		time = new Timestamp(date.getTime());
		WeeklyPlanning wp3 = new WeeklyPlanning("2015 - s.3", list, time);
		date = dateFormat.parse("25/01/2015");
		time = new Timestamp(date.getTime());
		WeeklyPlanning wp4 = new WeeklyPlanning("2015 - s.4", list, time);
		date = dateFormat.parse("01/02/2015");
		time = new Timestamp(date.getTime());
		WeeklyPlanning wp5 = new WeeklyPlanning("2015 - s.5", list, time);
		date = dateFormat.parse("08/02/2015");
		time = new Timestamp(date.getTime());
		WeeklyPlanning wp6 = new WeeklyPlanning("2015 - s.6", list, time);
		date = dateFormat.parse("15/02/2015");
		time = new Timestamp(date.getTime());
		WeeklyPlanning wp7 = new WeeklyPlanning("2015 - s.7", list, time);
		weeklyPlanningService.save(wp1);
		weeklyPlanningService.save(wp2);
		weeklyPlanningService.save(wp3);
		weeklyPlanningService.save(wp4);
		weeklyPlanningService.save(wp5);
		weeklyPlanningService.save(wp6);
		weeklyPlanningService.save(wp7);
		
		// Get the 5 weekly plannings (start index, count elements)
		List<WeeklyPlanning> listFounds = weeklyPlanningService.findWeeklyPlannings(0, 5);
		assertTrue(listFounds.size() == 5);
		// Must be order desc
		assertEquals(wp7.getName(), listFounds.get(0).getName());
		assertEquals(wp6.getName(), listFounds.get(1).getName());
		assertEquals(wp5.getName(), listFounds.get(2).getName());
		assertEquals(wp4.getName(), listFounds.get(3).getName());
		assertEquals(wp3.getName(), listFounds.get(4).getName());
		
		// Get the next 5 weekly plannings
		listFounds = weeklyPlanningService.findWeeklyPlannings(5, 5);
		assertTrue(listFounds.size() == 2);
		assertEquals(wp2.getName(), listFounds.get(0).getName());
		assertEquals(wp1.getName(), listFounds.get(1).getName());
		
	}
	
	@Test
	public void testGetPlannings() throws DuplicateInstanceException
	{
		
		WeeklyPlanning weeklyPlanning = new WeeklyPlanning("weeklyPlanning");
		weeklyPlanningService.save(weeklyPlanning);
		
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		Training training3 = new Training("training3");
		trainingService.save(training1);
		trainingService.save(training2);
		trainingService.save(training3);
		
		Planning planning1 = new Planning("planning1", weeklyPlanning, training1);
		Planning planning2 = new Planning("planning2", weeklyPlanning, training2);
		Planning planning3 = new Planning("planning3", weeklyPlanning, training3);
		Set<Planning> plannings = new HashSet<Planning>();
		plannings.add(planning1);
		plannings.add(planning2);
		plannings.add(planning3);
		
		weeklyPlanning.setPlannings(plannings);
		
		List<Planning> planningsAsc = weeklyPlanningService.orderByTrainingAsc(weeklyPlanning);
		assertTrue(planningsAsc.size() == 3);
		assertEquals(planning1.getName(), planningsAsc.get(0).getName());
		assertEquals(planning2.getName(), planningsAsc.get(1).getName());
		assertEquals(planning3.getName(), planningsAsc.get(2).getName());
		
		List<Planning> planningsDesc = weeklyPlanningService
				.orderByTrainingDesc(weeklyPlanning);
		assertTrue(planningsDesc.size() == 3);
		assertEquals(planning3.getName(), planningsDesc.get(0).getName());
		assertEquals(planning2.getName(), planningsDesc.get(1).getName());
		assertEquals(planning1.getName(), planningsDesc.get(2).getName());
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPublishPlanning() throws ParseException, DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		training2.setStatus(false);
		trainingService.save(training1);
		trainingService.save(training2);
		
		UserProfile user1 = userService.registerUser("user1@gmail.com", "user",
				new UserProfileDetails("n", "b", 0, "a"), new Role("r"));
		userService.assignTraining(user1.getUserProfileId(), training1);
		
		Planning planning1 = new Planning();
		planning1.setName("planing1");
		
		List<Training> trainings = trainingService.getActiveTrainings();
		assertTrue(trainings.size() == 1);
				
		//Training, Planning
		weeklyPlanningService.publishPlanning(trainings.get(0), planning1);
		
		assertTrue(planning1.getName().compareTo(user1.getActualPlanning.getName()));
		assertTrue(planning1.getCreationDate().equals(
				user1.getActualPlanning.getCreationDate()));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPublishAllPlannings() throws ParseException, DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		trainingService.save(training1);
		trainingService.save(training2);
		
		UserProfile user1 = userService.registerUser("user1@gmail.com", "user",
				new UserProfileDetails("n", "b", 0, "a"), new Role("r"));
		userService.assignTraining(user1.getUserProfileId(), training1);
		UserProfile user2 = userService.registerUser("user2@gmail.com", "user",
				new UserProfileDetails("n", "b", 0, "a"), new Role("r"));
		userService.assignTraining(user2.getUserProfileId(), training2);
		
		Planning planning1 = new Planning();
		planning1.setName("planing1");
		Planning planning2 = new Planning();
		planning2.setName("planing2");
		List<Planning> plannings = new ArrayList<Planning>();
		plannings.add(planning1);
		plannings.add(planning2);
		
		List<Training> trainings = trainingService.getActiveTrainings();
		
		//List<Training>, List<Planning>
		weeklyPlanningService.publishPlannings(trainings, plannings);
		
		assertTrue(planning1.getName().compareTo(user1.getActualPlanning.getName()));
		assertTrue(planning1.getCreationDate().equals(
				user1.getActualPlanning.getCreationDate()));
		
		assertTrue(planning2.getName().compareTo(user2.getActualPlanning.getName()));
		assertTrue(planning2.getCreationDate().equals(
				user2.getActualPlanning.getCreationDate()));
	}
	
}
