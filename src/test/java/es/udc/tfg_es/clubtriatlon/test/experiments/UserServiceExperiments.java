package es.udc.tfg_es.clubtriatlon.test.experiments;
/* BSD License */

import static es.udc.tfg_es.clubtriatlon.model.util.GlobalNames.SPRING_CONFIG_FILE;
import static es.udc.tfg_es.clubtriatlon.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.udc.tfg_es.clubtriatlon.model.role.Role;
import es.udc.tfg_es.clubtriatlon.model.userprofile.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.userservice.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserService;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

public class UserServiceExperiments {

	public static void main(String[] args) {

		/* Get service object. */
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE });
		UserService userService = ctx.getBean(UserService.class);

		try {
			Role role = new Role("user");
			// Register user.
			UserProfile userProfile = userService.registerUser("serviceUser",
					"userPassword", new UserProfileDetails("name", "1980/01/23",
							601601601, "account"), role);
			System.out.println("User with userId '"
					+ userProfile.getUserProfileId() + "' has been created");
			System.out.println(userProfile);

			// Find user.
			userProfile = userService.login("serviceUser", "userPassword",
					false);
			System.out.println("User with userId '"
					+ userProfile.getUserProfileId() + "' has been retrieved");
			System.out.println(userProfile);

			// ... proceed in the same way for other entities / methods / use
			// cases

		} catch (IncorrectPasswordException | InstanceNotFoundException
				| DuplicateInstanceException e) {
			e.printStackTrace();
		}

	}

}
