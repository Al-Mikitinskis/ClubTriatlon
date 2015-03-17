package es.udc.tfg_es.clubtriatlon.model.userservice;

import es.udc.tfg_es.clubtriatlon.model.userprofile.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

public interface UserService {

    public UserProfile registerUser(String loginName, String clearPassword,
            UserProfileDetails userProfileDetails)
            throws DuplicateInstanceException;

    public UserProfile login(String loginName, String password,
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

}
