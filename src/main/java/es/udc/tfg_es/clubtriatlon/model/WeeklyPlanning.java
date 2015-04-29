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

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
// @Immutable /*Como Pedido -> lineas pedido*/
public class WeeklyPlanning {
	
	private Long			weeklyPlanningId;
	private String			name;
	private Timestamp		creationDate;
	private Set<Planning>	plannings;
	
	public WeeklyPlanning() {}
	
	public WeeklyPlanning(String name, Set<Planning> plannings) {
		
		/*
		 * NOTE: "id" *must* be left as "null" since its value is automatically
		 * generated.
		 */
		this.name = name;
		this.plannings = plannings;
		
	}
	
	@Column(name = "id")
	@SequenceGenerator( // It only takes effect for db providing id generators.
	name = "WeeklyPlanningIdGenerator", sequenceName = "WeeklyPlanningSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "WeeklyPlanningIdGenerator")
	public Long getWeeklyPlanningId() {
		return this.weeklyPlanningId;
	}
	
	public void setWeeklyPlanningId(Long weeklyPlanningId) {
		this.weeklyPlanningId = weeklyPlanningId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "cDate")
	public Timestamp getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	@OneToMany(mappedBy = "weeklyPlanning")
	public Set<Planning> getPlannings() {
		return plannings;
	}
	
	public void setPlannings(Set<Planning> plannings) {
		this.plannings = plannings;
	}
	
	public void addPlanning(Planning planning) {
		this.plannings.add(planning);
	}
	
}
