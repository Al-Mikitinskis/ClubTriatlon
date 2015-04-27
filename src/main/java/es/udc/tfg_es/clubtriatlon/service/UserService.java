package es.udc.tfg_es.clubtriatlon.service;
/* BSD License */

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.utils.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.utils.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

public interface UserService {

    public UserProfile registerUser(String email, String clearPassword,
            UserProfileDetails userProfileDetails, Role role)
            throws DuplicateInstanceException;

    public UserProfile login(String email, String password,
            boolean passwordIsEncrypted) throws InstanceNotFoundException,
            IncorrectPasswordException;

    public UserProfile findUserProfile(Long userProfileId)
            throws InstanceNotFoundException;

    public void updateUserProfileDetails(Long userProfileId,
            UserProfileDetails userProfileDetails)
            throws InstanceNotFoundException;

    public void changePassword(Long userProfileId, String oldClearPassword,
            String newClearPassword) throws IncorrectPasswordException,
            InstanceNotFoundException;
    
    public UserProfile findUserProfileByEmail(String email) throws InstanceNotFoundException;

}
