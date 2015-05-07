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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.model.RoleDao;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao	roleDao;
	
	public void save(Role role)
	{
		roleDao.save(role);
	}
	
	@Transactional(readOnly = true)
	public Role getRoleById(Long roleId) throws InstanceNotFoundException
	{
		return roleDao.find(roleId);
	}
	
	@Transactional(readOnly = true)
	public Role getRoleByName(String name) throws InstanceNotFoundException
	{
		return roleDao.getRoleByName(name);
	}
	
	@Transactional(readOnly = true)
	public String getRoleNameByUserEmail(String email) throws InstanceNotFoundException
	{
		return roleDao.getRoleNameByUserEmail(email);
	}
	
	@Transactional(readOnly = true)
	public List<Role> findAllRoles()
	{
		return roleDao.findAllRoles();
	}
	
}
