package es.udc.tfg_es.clubtriatlon.web.util;
/* BSD License */

public class UserSession {

	private Long userProfileId;
	private String name;

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
