package es.udc.tfg_es.clubtriatlon.model.userservice;
/* BSD License */

import es.udc.tfg_es.clubtriatlon.model.role.Role;
import es.udc.tfg_es.clubtriatlon.model.userprofile.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

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
