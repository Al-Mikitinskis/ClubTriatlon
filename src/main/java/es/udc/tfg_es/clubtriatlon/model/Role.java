package es.udc.tfg_es.clubtriatlon.model;
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Role {

	private Long   id;
	private String name;

	public Role() {
	}

	public Role(String name) {
		
		 /* NOTE: "id" *must* be left as "null" since its value
         * is automatically generated. */
		this.name = name;
		
	}
	
	@Column(name = "roleId")
	@SequenceGenerator(                // It only takes effect for
			name = "RoleIdGenerator",  // databases providing identifier
			sequenceName = "RoleSeq")  // generators.
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="RoleIdGenerator")
	public Long getId(){
		return this.id;
	}
	    
	public void setId(Long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
