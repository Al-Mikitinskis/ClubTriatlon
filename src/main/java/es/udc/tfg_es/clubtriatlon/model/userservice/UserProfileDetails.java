package es.udc.tfg_es.clubtriatlon.model.userservice;

public class UserProfileDetails {

	private String firstName;
	private String lastName;
	private String email;
	private String road;
	private String num;
	private int   postalCode;
	
	public UserProfileDetails(String firstName, String lastName, String email,
				String road, String num, int postalCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.road = road;
		this.num = num;
		this.postalCode = postalCode;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getRoad() {
		return road;
	}

	public String getNum() {
		return num;
	}

	public int getPostalCode() {
		return postalCode;
	}

}
