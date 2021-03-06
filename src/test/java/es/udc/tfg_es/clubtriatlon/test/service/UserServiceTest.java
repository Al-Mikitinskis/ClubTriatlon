package es.udc.tfg_es.clubtriatlon.test.service;
/* BSD License */

import static es.udc.tfg_es.clubtriatlon.utils.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.tfg_es.clubtriatlon.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.service.RoleService;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.utils.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.utils.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.service.UserService;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class UserServiceTest {

    private final long NON_EXISTENT_USER_PROFILE_ID = -1;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @Test
    public void testRegisterUserAndFindUserProfile()
        throws DuplicateInstanceException, InstanceNotFoundException {

    	Role role = new Role("user");
    	roleService.save(role);
    	
        // Register user and find profile.
        UserProfile userProfile = userService.registerUser(
            "user", "userPassword",
            new UserProfileDetails("name", "1980/01/23", 601601601, "account"),
            role);

        UserProfile userProfile2 = userService.findUserProfile(
            userProfile.getUserProfileId());

        // Check data.
        assertEquals(userProfile, userProfile2);

    }

    @Test(expected = DuplicateInstanceException.class)
    public void testRegisterDuplicatedUser() throws DuplicateInstanceException {

        String email = "user";
        String clearPassword = "userPassword";
        UserProfileDetails userProfileDetails = 
        		new UserProfileDetails("name", "1980/01/23", 601601601, "account");
        Role role = new Role("user");
        roleService.save(role);
        userService.registerUser(email, clearPassword,
            userProfileDetails, role);

        userService.registerUser(email, clearPassword,
            userProfileDetails, role);

    }

    @Test
    public void testLoginClearPassword() throws IncorrectPasswordException,
        InstanceNotFoundException {

        String clearPassword = "userPassword";
        UserProfile userProfile = registerUser("user", clearPassword);

        UserProfile userProfile2 = userService.login(userProfile.getEmail(),
            clearPassword, false);

        assertEquals(userProfile, userProfile2);

    }

    @Test
    public void testLoginEncryptedPassword() throws IncorrectPasswordException,
            InstanceNotFoundException {

        UserProfile userProfile = registerUser("user", "clearPassword");

        UserProfile userProfile2 = userService.login(userProfile.getEmail(),
            userProfile.getEncryptedPassword(), true);

        assertEquals(userProfile, userProfile2);

    }

    @Test(expected = IncorrectPasswordException.class)
    public void testLoginIncorrectPasword() throws IncorrectPasswordException,
            InstanceNotFoundException {

        String clearPassword = "userPassword";
        UserProfile userProfile = registerUser("user", clearPassword);

        userService.login(userProfile.getEmail(), 'X' + clearPassword,
             false);

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testLoginWithNonExistentUser()
            throws IncorrectPasswordException, InstanceNotFoundException {

        userService.login("user", "userPassword", false);

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testFindNonExistentUser() throws InstanceNotFoundException {

        userService.findUserProfile(NON_EXISTENT_USER_PROFILE_ID);

    }

    @Test
    public void testUpdate() throws InstanceNotFoundException,
            IncorrectPasswordException {

        // Update profile.
        String clearPassword = "userPassword";
        UserProfile userProfile = registerUser("user", clearPassword);

        UserProfileDetails newUserProfileDetails = new UserProfileDetails(
            "name", "1980/01/23", 601601601, "account");

        userService.updateUserProfileDetails(userProfile.getUserProfileId(),
            newUserProfileDetails);

        // Check changes.
        userService.login(userProfile.getEmail(), clearPassword, false);
        UserProfile userProfile2 = userService.findUserProfile(
            userProfile.getUserProfileId());

        assertEquals(newUserProfileDetails.getName(),
            userProfile2.getName());
        assertEquals(newUserProfileDetails.getBirthDate(),
            userProfile2.getBirthDate());
        assertEquals(newUserProfileDetails.getPhoneNumber(),
            userProfile2.getPhoneNumber());

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testUpdateWithNonExistentUser()
            throws InstanceNotFoundException {

        userService.updateUserProfileDetails(NON_EXISTENT_USER_PROFILE_ID,
            new UserProfileDetails("name", "1980/01/23", 601601601, "account"));

    }

    @Test
    public void testChangePassword() throws InstanceNotFoundException,
            IncorrectPasswordException {

        // Change password.
        String clearPassword = "userPassword";
        UserProfile userProfile = registerUser("user", clearPassword);
        String newClearPassword = 'X' + clearPassword;

        userService.changePassword(userProfile.getUserProfileId(),
            clearPassword, newClearPassword);

        // Check new password.
        userService.login(userProfile.getEmail(), newClearPassword, false);

    }

    @Test(expected = IncorrectPasswordException.class)
    public void testChangePasswordWithIncorrectPassword()
            throws InstanceNotFoundException, IncorrectPasswordException {

        String clearPassword = "userPassword";
        UserProfile userProfile = registerUser("user", clearPassword);

        userService.changePassword(userProfile.getUserProfileId(),
            'X' + clearPassword, 'Y' + clearPassword);

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testChangePasswordWithNonExistentUser()
            throws InstanceNotFoundException, IncorrectPasswordException {

        userService.changePassword(NON_EXISTENT_USER_PROFILE_ID,
                "userPassword", "XuserPassword");

    }

    private UserProfile registerUser(String email, String clearPassword) {

        UserProfileDetails userProfileDetails = new UserProfileDetails(
            "name", "1980/01/23", 89910, "account");
        Role role = new Role("user");
        roleService.save(role);
        try {
            return userService.registerUser(
                email, clearPassword, userProfileDetails, role);

        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }

    }

}
