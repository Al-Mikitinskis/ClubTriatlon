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
import static es.udc.tfg_es.clubtriatlon.test.util.GlobalNames.*;
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
public class RoleWebTest {

	@Test
	public void testCheckSuccesAuthz() {
    	
		WebDriver driver = SeleniumMethods.auntenticateAdmin();
		//Can see his menu
		WebElement planningLink = 
				driver.findElement(By.id("menuOptions")).findElement(By.id("planningWeekly"));
		assertEquals(planningLink.getText(), "Entrenamientos");
		planningLink.click();
		assertEquals(driver.getCurrentUrl(), 
				"http://localhost:9090/triatlon/admin/plannings/planningweekly/0");
		SeleniumMethods.logout(driver);
		driver.quit();

	}
    
	@Test
	public void testCheckNoAuthz() {

		WebDriver driver = SeleniumMethods.auntenticateUser();
		//Can't see "Administrador" menu because no has his role, must go with url
		driver.get("http://localhost:9090/triatlon/admin/plannings/PlanningWeekly");
        //Can see the unauthorized message and the "Ir a inicio" link
        assertNotNull(driver.findElement(By.id("indexPage")));
        assertEquals(driver.findElement(By.id("indexPage")).getText(), "Ir a inicio");

	}

}
