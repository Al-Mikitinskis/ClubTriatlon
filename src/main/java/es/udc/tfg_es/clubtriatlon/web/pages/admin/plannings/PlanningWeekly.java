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

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.tfg_es.clubtriatlon.model.WeeklyPlanning;
import es.udc.tfg_es.clubtriatlon.service.WeeklyPlanningService;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.web.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
// @RequiresRoles("Administrador") /* sería suficiente si unauthorizedUrl de
// shiroFilter funcionara */
public class PlanningWeekly {
	
	public boolean isAdmin() {
		return SecurityUtils.getSubject().hasRole("Administrador");
	}
	
	private int						startIndex		= 0;
	private final static int		ITEMS_PER_PAGE	= 5;
	private WeeklyPlanning			weeklyPlanning;
	private List<WeeklyPlanning>	weeklyPlannings	= new ArrayList<WeeklyPlanning>();
	
	@Property
	@SessionState(create = false)
	private UserSession				userSession;
	
	@Inject
	private WeeklyPlanningService	weeklyPlanningService;
	
	@InjectComponent
	private Zone					weeksListZone;
	
	public List<WeeklyPlanning> getWeeklyPlannings() {
		return this.weeklyPlannings;
	}
	
	public WeeklyPlanning getWeeklyPlanning() {
		return this.weeklyPlanning;
	}
	
	public void setWeeklyPlanning(WeeklyPlanning weeklyPlanning) {
		this.weeklyPlanning = weeklyPlanning;
	}
	
	public boolean getNextLinkContext() {
		return ((ITEMS_PER_PAGE + 1) == weeklyPlanningService.findWeeklyPlannings(startIndex,
				ITEMS_PER_PAGE + 1).size());
	}
	
	public boolean getPreviousLinkContext() {
		return (startIndex - ITEMS_PER_PAGE >= 0);
	}
	
	Object onActionFromPreviousLink() {
		onActivate(startIndex - ITEMS_PER_PAGE);
		return weeksListZone.getBody();
	}
	
	Object onActionFromNextLink() {
		onActivate(startIndex + ITEMS_PER_PAGE);
		return weeksListZone.getBody();
	}
	
	Object[] onPassivate() {
		return new Object[] { startIndex };
	}
	
	void onActivate(int startIndex) {
		this.startIndex = startIndex;
		this.weeklyPlannings = weeklyPlanningService.findWeeklyPlannings(startIndex,
				ITEMS_PER_PAGE);
	}
	
}
