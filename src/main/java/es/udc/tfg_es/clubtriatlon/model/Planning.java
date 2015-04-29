package es.udc.tfg_es.clubtriatlon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Planning {
	
	private Long			id;
	private String			name;
	private WeeklyPlanning	weeklyPlanning;
	
	// TODO demás atributos
	
	public Planning() {}
	
	public Planning(String name) {
		
		/*
		 * NOTE: "id" *must* be left as "null" since its value is automatically
		 * generated.
		 */
		this.name = name;
		
	}
	
	@SequenceGenerator( // It only takes effect for db providing id generators.
	name = "PlanningIdGenerator", sequenceName = "PlanningSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PlanningIdGenerator")
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weeklyPlanningId")
	public WeeklyPlanning getWeeklyPlanning() {
		return weeklyPlanning;
	}
	
	public void setWeeklyPlanning(WeeklyPlanning weeklyPlanning) {
		this.weeklyPlanning = weeklyPlanning;
	}
	
}
