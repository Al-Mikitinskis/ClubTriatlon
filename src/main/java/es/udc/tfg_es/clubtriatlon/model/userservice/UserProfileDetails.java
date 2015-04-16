package es.udc.tfg_es.clubtriatlon.model.userservice;

public class UserProfileDetails {

	private String name;
	private String birthDate;
	private int    phoneNumber;
	private String account;
	
	public UserProfileDetails(String name, String birthDate,
			int phoneNumber, String account) {
		this.name        = name;
		this.birthDate   = birthDate;
		this.phoneNumber = phoneNumber;
		this.account     = account;
	}

	public String getName() {
		return name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getAccount() {
		return account;
	}

}
