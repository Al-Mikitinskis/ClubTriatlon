package es.udc.tfg_es.clubtriatlon.test.model.roleservice;
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

import static es.udc.tfg_es.clubtriatlon.model.util.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.tfg_es.clubtriatlon.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.role.Role;
import es.udc.tfg_es.clubtriatlon.model.role.RoleDao;
import es.udc.tfg_es.clubtriatlon.model.roleservice.RoleService;
import es.udc.tfg_es.clubtriatlon.model.userservice.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserService;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class RoleServiceTest {

    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testCreateAndFindRole() throws DuplicateInstanceException,
        InstanceNotFoundException, IncorrectPasswordException {

    	Role newRole = new Role("admin");
    	roleDao.save(newRole);
    	
    	//Find by name
    	Long id = roleService.getRoleByName("admin").getId();
    	assertTrue(id != null && id == newRole.getId());
    	
    	//Find by id
    	assertTrue( id == roleService.
    			getRoleById(roleService.getRoleByName("admin").getId()).
    			getId() );

    }
    
    @Test
    public void testgetAllRoles() throws DuplicateInstanceException,
        InstanceNotFoundException, IncorrectPasswordException {

    	assertTrue(roleService.findAllRoles().size() == 0);
    	
    	Role adminRole = new Role("admin");
    	roleDao.save(adminRole);
    	Role userRole = new Role("user");
    	roleDao.save(userRole);

    	assertTrue(roleService.findAllRoles().size() == 2);
    	
    	//List order desc ==> first: user second: admin
    	assertTrue(roleService.findAllRoles().get(0).getName()
    			.compareTo("user") == 0);
    	assertTrue(roleService.findAllRoles().get(1).getName()
    			.compareTo("admin") == 0);

    }

    @Test //Needs App running (mvn jetty:run) and uses the real database, not test database
    public void testCheckUserRole() throws DuplicateInstanceException,
        IncorrectPasswordException, InstanceNotFoundException {
    	
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:9090/triatlon/");
        
        //User with "Administrador" role
        driver.findElement(By.linkText("Autenticarse")).click();
        driver.getCurrentUrl(); //Update the Url
        driver.findElement(By.name("email")).sendKeys("admin1@triatlon.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.id("loginForm")).findElement(By.id("loginButton")).click();
        //Can see his menu
        WebElement planningLink = driver.findElement(By.id("sidebar")).findElement(By.id("planning"));
        assertEquals(planningLink.getText(), "Planes de entrenamiento");
        planningLink.click();
        assertEquals(driver.getCurrentUrl(), "http://localhost:9090/triatlon/admin/planning");

        driver.findElement(By.id("logout")).click();

        //User with "Usuario" role
        driver.findElement(By.linkText("Autenticarse")).click();
        driver.getCurrentUrl(); //Update the Url
        driver.findElement(By.name("email")).sendKeys("user3C@gmail.com");
        driver.findElement(By.name("password")).sendKeys("user");
        driver.findElement(By.id("loginForm")).findElement(By.id("loginButton")).click();
        //Can't see "Administrador" menu because no has his role, must go with url
        driver.get("http://localhost:9090/triatlon/admin/planning");
        //Can see the unauthorized message and the "Ir a inicio" link
        assertNotNull(driver.findElement(By.id("indexPage")));
        assertEquals(driver.findElement(By.id("indexPage")).getText(), "Ir a inicio");

    	driver.quit();
    }

}
