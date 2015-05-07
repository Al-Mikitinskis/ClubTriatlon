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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

@Entity
public class Training {
	
	private Long				trainingId;
	private String				name;
	private boolean				status;
	private Set<Planning>		plannings;
	private Set<UserProfile>	users;
	
	public Training() {}
	
	public Training(String name)
	{
		/*
		 * NOTE: "id" *must* be left as "null" since its value is automatically
		 * generated.
		 */
		this.name = name;
		this.status = true;
		this.plannings = new HashSet<Planning>();
		this.users = new HashSet<UserProfile>();
	}
	
	public Training(String name, Set<Planning> plannings)
	{
		this.name = name;
		this.status = true;
		this.plannings = plannings;
		this.users = new HashSet<UserProfile>();
	}
	
	@Column(name = "id")
	@SequenceGenerator( // It only takes effect for db providing id generators.
	name = "TrainingIdGenerator", sequenceName = "TrainingSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TrainingIdGenerator")
	public Long getTrainingId()
	{
		return this.trainingId;
	}
	
	public void setTrainingId(Long trainingId)
	{
		this.trainingId = trainingId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Column(name = "status", columnDefinition = "BIT", length = 1)
	@Type(type = "org.hibernate.type.BooleanType")
	public boolean isStatus()
	{
		return status;
	}
	
	public void setStatus(boolean status)
	{
		this.status = status;
	}
	
	@OneToMany(mappedBy = "training", fetch = FetchType.LAZY)
	public Set<Planning> getPlannings()
	{
		return plannings;
	}
	
	public void setPlannings(Set<Planning> plannings)
	{
		this.plannings = plannings;
	}
	
	public void addPlanning(Planning planning)
	{
		this.plannings.add(planning);
	}
	
	@OneToMany(mappedBy = "training", fetch = FetchType.LAZY)
	public Set<UserProfile> getUsers()
	{
		return users;
	}
	
	public void setUsers(Set<UserProfile> users)
	{
		this.users = users;
	}
	
	public void addUser(UserProfile user)
	{
		this.users.add(user);
	}
	
}
