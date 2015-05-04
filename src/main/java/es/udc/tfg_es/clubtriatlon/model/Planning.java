package es.udc.tfg_es.clubtriatlon.model;

import java.sql.Timestamp;

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
//	private byte[]			document;
	private Timestamp		creationDate;
	private WeeklyPlanning	weeklyPlanning;
	private Training		training;
	
	// TODO demás atributos
	
	public Planning() {}
	
	public Planning(String name, /*byte[] document,*/ WeeklyPlanning weeklyPlanning,
			Training training) {
		
		/*
		 * NOTE: "id" *must* be left as "null" since its value is automatically
		 * generated.
		 */
		this.name = name;
//		this.document = document;
		this.weeklyPlanning = weeklyPlanning;
		this.training = training;
		
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
	
//	public byte[] getDocument() {
//		return document;
//	}
//	
//	public void setDocument(byte[] document) {
//		this.document = document;
//	}
	
	@Column(name = "cDate")
	public Timestamp getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wpId")
	public WeeklyPlanning getWeeklyPlanning() {
		return weeklyPlanning;
	}
	
	public void setWeeklyPlanning(WeeklyPlanning weeklyPlanning) {
		this.weeklyPlanning = weeklyPlanning;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tId")
	public Training getTraining() {
		return training;
	}
	
	public void setTraining(Training training) {
		this.training = training;
	}
}
