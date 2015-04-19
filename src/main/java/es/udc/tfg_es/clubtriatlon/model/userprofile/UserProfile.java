package es.udc.tfg_es.clubtriatlon.model.userprofile;
/* BSD License */

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import es.udc.tfg_es.clubtriatlon.model.role.Role;

@Entity
public class UserProfile {

	private Long   userProfileId;
	private String email;
	private String encryptedPassword;
	private String name;
	private String birthDate;
	private int    phoneNumber;
	private String account;
	private Role   role;

	public UserProfile() {
	}

	public UserProfile(String email, String encryptedPassword, String name,
			String birthDate, int phoneNumber, String account, Role role) {
		
		/* NOTE: "userProfileId" *must* be left as "null" since its value is
		 * automatically generated. */
		
		this.email             = email;
		this.encryptedPassword = encryptedPassword;
		this.name              = name;
		this.birthDate         = birthDate;
		this.phoneNumber       = phoneNumber;
		this.account           = account;
		this.role              = role;
	}

	@Column(name = "usrId")
	@SequenceGenerator(              // It only takes effect for
	name = "UserProfileIdGenerator", // databases providing identifier
	sequenceName = "UserProfileSeq") // generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserProfileIdGenerator")
	
	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "enPassword")
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "role")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserProfile [userProfileId=" + userProfileId + ", email="
				+ email + ", encryptedPassword=" + encryptedPassword
				+ ", name=" + name + ", birthDate=" + birthDate
				+ ", phoneNumber=" + phoneNumber + ", account=" + account + "]";
	}

}
