package es.udc.tfg_es.clubtriatlon.model.role;
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

import org.springframework.stereotype.Repository;

import es.udc.tfg_es.clubtriatlon.model.util.dao.GenericDaoHibernate;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

@Repository("RoleDao")
public class RoleDaoHibernate extends
		GenericDaoHibernate<Role, Long> implements RoleDao {

	public String getRoleNameByUserEmail(String email) throws InstanceNotFoundException {

    	Role role = (Role) getSession().createQuery(
    			"SELECT r FROM UserProfile u INNER JOIN Role r ON (u.role = r.id)" +
    			"WHERE u.email = :email")
    			.setParameter("email", email)
    			.uniqueResult();
    	if (role == null) {
   			throw new InstanceNotFoundException(email, Role.class.getName());
    	} else {
    		return role.getName();
    	}

	}
	
    public Role getRoleById(Long id) throws InstanceNotFoundException {
    	Role role = (Role) getSession().createQuery(
    			"SELECT r FROM Role r WHERE r.id = :id")
    			.setParameter("id", id)
    			.uniqueResult();
    	if (role == null) {
   			throw new InstanceNotFoundException(id, Role.class.getName());
    	} else {
    		return role;
    	}
    }
	
    public Role getRoleByName(String name) throws InstanceNotFoundException {
    	Role role = (Role) getSession().createQuery(
    			"SELECT r FROM Role r WHERE r.name = :name")
    			.setParameter("name", name)
    			.uniqueResult();
    	if (role == null) {
   			throw new InstanceNotFoundException(name, Role.class.getName());
    	} else {
    		return role;
    	}
    }
    
    @SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
    	return getSession().createQuery(
    			"SELECT r FROM Role r ORDER BY r.name DESC")
    			.list();
    }


}