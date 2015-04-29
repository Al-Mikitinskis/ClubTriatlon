package es.udc.tfg_es.clubtriatlon.test.web;

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

import static es.udc.tfg_es.clubtriatlon.utils.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.tfg_es.clubtriatlon.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.test.util.SeleniumMethods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class AdminPlanningWebTest {
	
	@Test
	public void testTrainingsManagementView() {
		
		WebDriver driver = SeleniumMethods.auntenticateAdmin();
		// Access to Planning Weekly view
		driver.findElement(By.id("menuOptions")).findElement(By.id("planningWeekly")).click();
		driver.getCurrentUrl();
		// Access to Trainings Management view
		WebElement trainingsManagementLink = driver.findElement(By.id("trainingsManagement"));
		assertEquals(trainingsManagementLink.getText(), "Gestión de entrenamientos");
		trainingsManagementLink.click();
		assertEquals(driver.getCurrentUrl(),
				"http://localhost:9090/triatlon/admin/plannings/trainingsmanagement");
		
		SeleniumMethods.logout(driver);
		driver.quit();
		
	}
	
	@Test
	public void testNewWeeklyPlanningView() {
		
		WebDriver driver = SeleniumMethods.auntenticateAdmin();
		// Access to planningweekly view
		driver.findElement(By.id("menuOptions")).findElement(By.id("planningWeekly")).click();
		driver.getCurrentUrl();
		// Access to New Weekly Planning view
		WebElement newWeeklyPlanningLink = driver.findElement(By.id("newWeeklyPlanning"));
		assertEquals(newWeeklyPlanningLink.getText(), "+ Nuevo plan semanal");
		newWeeklyPlanningLink.click();
		assertEquals(driver.getCurrentUrl(),
				"http://localhost:9090/triatlon/admin/plannings/newweeklyplanning");
		
		SeleniumMethods.logout(driver);
		driver.quit();
		
	}
	
	@Test
	public void testWeeklyPlanningDetailsView() {
		
		WebDriver driver = SeleniumMethods.auntenticateAdmin();
		// Access to planningweekly view
		driver.findElement(By.id("menuOptions")).findElement(By.id("planningWeekly")).click();
		driver.getCurrentUrl();
		// Access to Weekly Planning Details view
		driver.findElement(By.id("weeksList")).findElement(By.linkText("2015 - s.7")).click();
		assertEquals(driver.getCurrentUrl(),
				"http://localhost:9090/triatlon/admin/plannings/weeklyplanningdetails/7");
		
		SeleniumMethods.logout(driver);
		driver.quit();
		
	}
	
	@Test
	public void testPlanningWeeklyNextPrevLinks() {
		
		WebDriver driver = SeleniumMethods.auntenticateAdmin();
		driver.findElement(By.id("menuOptions")).findElement(By.id("planningWeekly")).click();
		driver.getCurrentUrl();
		
		// Click "-->" link
		driver.findElement(By.id("weeksList")).
				findElement(By.id("nextLink")).click();
		assertNotNull(driver.findElement(By.id("weeksList")).findElement(
				By.partialLinkText("2015 - s.2")));
		assertNotNull(driver.findElement(By.id("weeksList")).findElement(
				By.partialLinkText("2015 - s.1")));
		
		//Selenium no detecta previousLink porque es por ajax?
//		driver.getCurrentUrl();
		// Click "<--" link
//		driver.findElement(By.id("weeksList")).
//				findElement(By.id("previousLink")).click();
//		assertNotNull(driver.findElement(By.id("weeksList")).findElement(
//				By.partialLinkText("2015 - s.7")));
		
		SeleniumMethods.logout(driver);
		driver.quit();
		
	}
	
}
