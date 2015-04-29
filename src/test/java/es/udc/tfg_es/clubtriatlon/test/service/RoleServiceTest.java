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

import static es.udc.tfg_es.clubtriatlon.utils.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.tfg_es.clubtriatlon.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.service.RoleService;
import es.udc.tfg_es.clubtriatlon.utils.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.service.UserService;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class RoleServiceTest {
	
	@Autowired
	private RoleService	roleService;
	
	@Autowired
	private UserService	userService;
	
	@Test
	public void testCreateAndFindRole() throws DuplicateInstanceException,
			InstanceNotFoundException, IncorrectPasswordException {
		
		Role newRole = new Role("admin");
		roleService.save(newRole);
		
		// Find by name
		Long id = roleService.getRoleByName("admin").getId();
		assertTrue(id != null && id == newRole.getId());
		
		// Find by id
		assertTrue(id == roleService.getRoleById(roleService.getRoleByName("admin").getId())
				.getId());
		
	}
	
	@Test
	public void testGetAllRoles() throws DuplicateInstanceException,
			InstanceNotFoundException, IncorrectPasswordException {
		
		assertTrue(roleService.findAllRoles().size() == 0);
		
		Role adminRole = new Role("admin");
		roleService.save(adminRole);
		Role userRole = new Role("user");
		roleService.save(userRole);
		
		assertTrue(roleService.findAllRoles().size() == 2);
		
		// List order desc ==> first: user second: admin
		assertTrue(roleService.findAllRoles().get(0).getName().compareTo("user") == 0);
		assertTrue(roleService.findAllRoles().get(1).getName().compareTo("admin") == 0);
		
	}
	
}
