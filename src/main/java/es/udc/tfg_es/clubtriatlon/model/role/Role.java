package es.udc.tfg_es.clubtriatlon.model.role;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.udc.tfg_es.clubtriatlon.model.userprofile.UserProfile;

@Entity
public class Role {

	private UserProfile user;
	private String name;

	public Role() {
	}

	public Role(UserProfile user, String name) {
		
		this.user = user;
		this.name = name;
		
	}
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userId")
	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	@Id /*Aunque no sea la clave primaria, hibernate necesita un identificador para la entidad*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [user=" + user + ", name=" + name + "]";
	}

}
