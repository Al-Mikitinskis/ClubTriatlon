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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.service.TrainingService;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;
import es.udc.tfg_es.clubtriatlon.model.Training;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class TrainingServiceTest {
	
	@Autowired
	private TrainingService	trainingService;
	
	@Test(expected = DuplicateInstanceException.class)
	public void testSaveDuplicateTraining() throws InstanceNotFoundException, DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		trainingService.save(training1);

		Training training2 = new Training("training1");
		trainingService.save(training2);
	}
	
	@Test
	public void testGetAllTrainings() throws InstanceNotFoundException, DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		Training training3 = new Training("training3");
		trainingService.save(training2);
		trainingService.save(training1);
		trainingService.save(training3);
		
		List<Training> listFounds = trainingService.getAllTrainings();
		assertTrue(listFounds.size() == 3);
		// Must be order by id (inserts)
		assertEquals(training2.getName(), listFounds.get(0).getName());
		assertEquals(training1.getName(), listFounds.get(1).getName());
		assertEquals(training3.getName(), listFounds.get(2).getName());
		
		trainingService.changeStatus(training2.getTrainingId());
		trainingService.remove(training2.getTrainingId());
		listFounds = trainingService.getAllTrainings();
		assertTrue(listFounds.size() == 2);
		assertEquals(training1.getName(), listFounds.get(0).getName());
		assertEquals(training3.getName(), listFounds.get(1).getName());
	}
	
	@Test
	public void testGetActiveTrainings() throws DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		Training training3 = new Training("training3");
		training2.setStatus(false);
		trainingService.save(training1);
		trainingService.save(training2);
		trainingService.save(training3);
		
		List<Training> listFounds = trainingService.getActiveTrainings();
		assertTrue(listFounds.size() == 2);
		// Must be order by name
		assertEquals(training1.getName(), listFounds.get(0).getName());
		assertEquals(training3.getName(), listFounds.get(1).getName());
	}
	
	@Test
	public void testGetAllTrainingsOrderByName() throws DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		Training training3 = new Training("training3");
		training1.setStatus(false);
		trainingService.save(training3);
		trainingService.save(training1);
		trainingService.save(training2);
		
		List<Training> listFounds = trainingService.orderByName(
				trainingService.getAllTrainings());
		assertTrue(listFounds.size() == 3);
		// Must be order by name
		assertEquals(training1.getName(), listFounds.get(0).getName());
		assertEquals(training2.getName(), listFounds.get(1).getName());
		assertEquals(training3.getName(), listFounds.get(2).getName());
	}
	
	@Test
	public void testGetAllTrainingsOrderByStatus() throws DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		Training training3 = new Training("training3");
		training1.setStatus(false);
		trainingService.save(training1);
		trainingService.save(training2);
		trainingService.save(training3);
		
		List<Training> listFounds = trainingService.orderByStatusActive(
				trainingService.getAllTrainings());
		assertTrue(listFounds.size() == 3);
		// Must be order by status (1st true)
		assertEquals(training2.getName(), listFounds.get(0).getName());
		assertEquals(training3.getName(), listFounds.get(1).getName());
		assertEquals(training1.getName(), listFounds.get(2).getName());
	}
	
	@Test
	public void testChangeStatus() throws InstanceNotFoundException, DuplicateInstanceException
	{
		Training training1 = new Training("training1");
		Training training2 = new Training("training2");
		training2.addUser(new UserProfile());
		trainingService.save(training1);
		trainingService.save(training2);
		
		assertTrue(training1.isStatus());
		assertTrue(training2.isStatus());
		
		trainingService.changeStatus(training1.getTrainingId());
		trainingService.changeStatus(training2.getTrainingId());
		
		assertFalse(training1.isStatus());
		assertTrue(training2.isStatus());
		
		trainingService.changeStatus(training1.getTrainingId());
		assertTrue(training1.isStatus());
	}
	
}
