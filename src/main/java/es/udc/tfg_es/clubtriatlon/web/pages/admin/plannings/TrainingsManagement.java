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

import es.udc.tfg_es.clubtriatlon.model.Training;
import es.udc.tfg_es.clubtriatlon.service.TrainingService;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

//import org.apache.tapestry5.corelib.mixins.Confirm;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class TrainingsManagement {
	
	public boolean isAdmin()
	{
		return SecurityUtils.getSubject().hasRole("Administrador");
	}
	
	@Property
	private List<Training>			trainings	= new ArrayList<Training>();
	
	@Property
	private Training				training;
	
	/**
	 * List sort order:
	 * 1 - by id
	 * 2 - by name
	 * 3 - by status
	 */
	@Property
	private int						order = 0;
	
	@Inject
	private TrainingService			trainingService;
	
//	@Property
//	@Persist(PersistenceConstants.FLASH)
//	private boolean					changeStatusErrorMessage;
	
	@Component
	private Form					newTrainingForm;
	
	@Property
	private String					newName;
	
	@Component(id = "newName")
	private TextField				newTrainingNameField;
	
	@Inject
	private Messages				messages;
	
	@InjectComponent
	private Zone					tableZone;
	
	@InjectComponent
	private Zone					formZone;
	
	@Inject
	private AjaxResponseRenderer	ajaxResponseRenderer;
	
//	public boolean isChangeStatusErrorMessage()
//	{
//		return this.changeStatusErrorMessage;
//	}

	private void doOrder(int order, List<Training> trainings)
	{
		switch (order) {
			case 2:
				this.trainings = trainingService.orderByStatusActive(trainings);
				break;
			case 1:
				this.trainings = trainingService.orderByName(trainings);
				break;
			case 0:
			default:
				this.trainings = trainingService.orderById(trainings);
				break;
		}
	}
	
	void onActionFromSortById()
	{
		doOrder(0, trainings);
		this.order = 0;
//		this.changeStatusErrorMessage = false;
		ajaxResponseRenderer.addRender(tableZone);
	}
	
	void onActionFromSortByName()
	{
		doOrder(1, trainings);
		this.order = 1;
//		this.changeStatusErrorMessage = false;
		ajaxResponseRenderer.addRender(tableZone);
	}
	
	void onActionFromSortByStatus()
	{
		doOrder(2, trainings);
		this.order = 2;
//		this.changeStatusErrorMessage = false;
		ajaxResponseRenderer.addRender(tableZone);
	}

	void onChangeStatus(Long trainingId) throws InstanceNotFoundException
	{
		trainingService.changeStatus(trainingId);
		ajaxResponseRenderer.addRender(tableZone);
//		if (this.changeStatusErrorMessage = !trainingService.changeStatus(trainingId)) {
//			ajaxResponseRenderer.addRender(tableZone);
//		}
	}
	
	void onValidateFromNewTrainingForm()
	{
		if (!newTrainingForm.isValid()) {
			return;
		}
		
		if (newName == null) {
			newTrainingForm.recordError(messages.get("emptyTrainingName"));
		}
		
		try {
			Training newTraining = new Training(newName);
			trainingService.save(newTraining);
			trainings.add(newTraining);
//			this.changeStatusErrorMessage = false;
			newName = "";
			ajaxResponseRenderer.addRender(tableZone);
			ajaxResponseRenderer.addRender(formZone);
		} catch (DuplicateInstanceException e) {
			newTrainingForm.recordError(newTrainingNameField,
					messages.get("trainingNameAlreadyExists"));
		}
	}
	
	void onActivate(int order)
	{
		this.order = order;
		doOrder(order, trainingService.getAllTrainings());
	}
	
	Object onPassivate()
	{
		return this.order;
	}
	
}
