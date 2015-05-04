package es.udc.tfg_es.clubtriatlon.web.pages.admin.plannings;

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

//import java.io.ByteArrayInputStream;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.tapestry5.annotations.InjectComponent;
//import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.tfg_es.clubtriatlon.model.Planning;
import es.udc.tfg_es.clubtriatlon.model.WeeklyPlanning;
import es.udc.tfg_es.clubtriatlon.service.PlanningService;
import es.udc.tfg_es.clubtriatlon.service.WeeklyPlanningService;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;

//import es.udc.tfg_es.clubtriatlon.web.services.PDFAttachment;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class WeeklyPlanningDetails {
	
	public boolean isAdmin() {
		return SecurityUtils.getSubject().hasRole("Administrador");
	}
	
	private Long					weeklyPlanningId;
	private WeeklyPlanning			weeklyPlanning;
	private Planning				planning;
	private boolean					order		= true;
	
	@Property
	private List<Planning>			plannings	= new ArrayList<Planning>();
	
	@Inject
	private WeeklyPlanningService	weeklyPlanningService;
	
	@Inject
	private PlanningService			planningService;
	
	@InjectComponent
	private Zone					tableZone;
	
	public void setWeeklyPlanningId(Long weeklyPlanningId) {
		this.weeklyPlanningId = weeklyPlanningId;
	}
	
	public WeeklyPlanning getWeeklyPlanning() {
		return weeklyPlanning;
	}
	
	public void setWeeklyPlanning(WeeklyPlanning weeklyPlanning) {
		this.weeklyPlanning = weeklyPlanning;
	}
	
	public Planning getPlanning() {
		return planning;
	}
	
	public void setPlanning(Planning planning) {
		this.planning = planning;
	}
	
	public void setOrder(boolean order) {
		this.order = order;
	}

	// public StreamResponse onSubmit(Long planningId) throws
	// InstanceNotFoundException,
	// IOException {
	Object onSubmit(Long planningId) throws InstanceNotFoundException {
		
		// this.planning = planningService.getPlanningById(planningId);
		// ByteArrayInputStream bais = new
		// ByteArrayInputStream(this.planning.getDocument());
		// return new PDFAttachment(bais, this.planning.getName());
		return this.getClass();
		
	}
	
	public boolean getSortByNameAscContext() {
		return this.order;
	}
	
	public boolean getSortByNameDescContext() {
		return !(this.order);
	}
	
	Object onActionFromSortByNameAsc() {
		this.plannings = weeklyPlanningService.orderByTrainingAsc(this.weeklyPlanning);
		setOrder(false);
		return tableZone.getBody();
	}
	
	Object onActionFromSortByNameDesc() {
		this.plannings = weeklyPlanningService.orderByTrainingDesc(this.weeklyPlanning);
		setOrder(true);
		return tableZone.getBody();
	}
	
	void onActivate(Long weeklyPlanningId) {
		
		this.weeklyPlanningId = weeklyPlanningId;
		
		try {
			this.weeklyPlanning = weeklyPlanningService.getWeeklyPlanningById(weeklyPlanningId);
			if (order) {
				this.plannings = weeklyPlanningService.orderByTrainingAsc(this.weeklyPlanning);
			} else {
				this.plannings = weeklyPlanningService.orderByTrainingDesc(this.weeklyPlanning);
			}
			this.order = !order;
		} catch (InstanceNotFoundException e) {}
		
	}
	
	Long onPassivate() {
		return weeklyPlanningId;
	}
	
}
