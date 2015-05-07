package es.udc.tfg_es.clubtriatlon.service;

/* BSD License */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.UserProfileDao;
import es.udc.tfg_es.clubtriatlon.utils.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.utils.PasswordEncrypter;
import es.udc.tfg_es.clubtriatlon.utils.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserProfileDao	userProfileDao;
	
	public UserProfile registerUser(String email, String clearPassword,
			UserProfileDetails userProfileDetails, Role role)
			throws DuplicateInstanceException
	{
		try {
			userProfileDao.findByEmail(email);
			throw new DuplicateInstanceException(email, UserProfile.class.getName());
		} catch (InstanceNotFoundException e) {
			String encryptedPassword = PasswordEncrypter.crypt(clearPassword);
			
			UserProfile userProfile = new UserProfile(email, encryptedPassword,
					userProfileDetails.getName(), userProfileDetails.getBirthDate(),
					userProfileDetails.getPhoneNumber(), userProfileDetails.getAccount(), role);
			
			userProfileDao.save(userProfile);
			
			return userProfile;
		}
	}
	
	@Transactional(readOnly = true)
	public UserProfile login(String email, String password, boolean passwordIsEncrypted)
			throws InstanceNotFoundException, IncorrectPasswordException
	{
		UserProfile userProfile = userProfileDao.findByEmail(email);
		String storedPassword = userProfile.getEncryptedPassword();
		
		if (passwordIsEncrypted) {
			if (!password.equals(storedPassword)) {
				throw new IncorrectPasswordException(email);
			}
		} else {
			if (!PasswordEncrypter.isClearPasswordCorrect(password, storedPassword)) {
				throw new IncorrectPasswordException(email);
			}
		}
		return userProfile;
	}
	
	@Transactional(readOnly = true)
	public UserProfile findUserProfile(Long userProfileId) throws InstanceNotFoundException
	{
		return userProfileDao.find(userProfileId);
	}
	
	public void updateUserProfileDetails(Long userProfileId,
			UserProfileDetails userProfileDetails) throws InstanceNotFoundException
	{
		UserProfile userProfile = userProfileDao.find(userProfileId);
		userProfile.setName(userProfileDetails.getName());
		userProfile.setBirthDate(userProfileDetails.getBirthDate());
		userProfile.setPhoneNumber(userProfileDetails.getPhoneNumber());
		userProfile.setAccount(userProfileDetails.getAccount());
	}
	
	public void changePassword(Long userProfileId, String oldClearPassword,
			String newClearPassword) throws IncorrectPasswordException,
			InstanceNotFoundException
	{
		
		UserProfile userProfile;
		userProfile = userProfileDao.find(userProfileId);
		
		String storedPassword = userProfile.getEncryptedPassword();
		
		if (!PasswordEncrypter.isClearPasswordCorrect(oldClearPassword, storedPassword)) {
			throw new IncorrectPasswordException(userProfile.getEmail());
		}
		
		userProfile.setEncryptedPassword(PasswordEncrypter.crypt(newClearPassword));
	}
	
	@Transactional(readOnly = true)
	public UserProfile findUserProfileByEmail(String email) throws InstanceNotFoundException
	{
		return userProfileDao.findByEmail(email);
	}
	
}
