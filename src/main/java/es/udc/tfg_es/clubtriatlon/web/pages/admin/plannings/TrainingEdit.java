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

import org.apache.shiro.SecurityUtils;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import es.udc.tfg_es.clubtriatlon.model.Training;
import es.udc.tfg_es.clubtriatlon.service.TrainingService;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class TrainingEdit {
	
	public boolean isAdmin()
	{
		return SecurityUtils.getSubject().hasRole("Administrador");
	}
	
	@Property
	private Training		training;
	
	private Long			trainingId;
	
	@Property
	private boolean			validTraining = true;
	
	@Property
	private int				order;
	
	@Property
	private String			name;
	
	@Component
	private Form			trainingEditForm;
	
	@Property
	private boolean			showConfirmDelete;
	
	@InjectComponent
	private Zone			deleteZone;
	
	@Inject
	private TrainingService	trainingService;
	
    @Inject
    private Request request;
    
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
	
	public void setTrainingId(Long trainingId)
	{
		this.trainingId = trainingId;
	}
	
	void onValidateFromTrainingEditForm()
	{
		try {
			if (!trainingEditForm.isValid()) {
				return;
			}
			trainingService.changeName(this.trainingId, this.name);
		} catch (InstanceNotFoundException e) {}
	}
	
	Object onSuccess()
	{
		return TrainingsManagement.class;
	}
	
	public boolean isShowConfirmDelete() {
	    return this.showConfirmDelete;
	}
	
	Object onDeleteShowConfirm() throws InstanceNotFoundException
	{
		this.showConfirmDelete = true;
		return deleteZone.getBody();
	}
	
	Object onDeleteYes() throws InstanceNotFoundException
	{
		trainingService.remove(trainingId);
		return TrainingsManagement.class;
	}
	
	Object onDeleteNo() throws InstanceNotFoundException
	{
		this.showConfirmDelete = false;
		return deleteZone.getBody();
	}
	
	void onActivate(Long trainingId, int order)
	{
		this.trainingId = trainingId;
		this.order = order;

		try {
			this.training = trainingService.getTrainingById(trainingId);
			this.name = training.getName();
			this.showConfirmDelete = false;
		} catch (InstanceNotFoundException e) {
			this.validTraining = false;
		}
	}
	
	Object onPassivate()
	{
		return new Object[] { trainingId, order };
	}

}
