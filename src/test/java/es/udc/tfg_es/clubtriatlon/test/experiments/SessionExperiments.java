package es.udc.tfg_es.clubtriatlon.test.experiments;
/* BSD License */

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.utils.PasswordEncrypter;

public class SessionExperiments {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Role role = new Role("user");
			// Register user.
			UserProfile userProfile = new UserProfile("sessionUser",
					PasswordEncrypter.crypt("userPassword"), "name",
					"1980/01/23", 601601601, "account", role);
			session.saveOrUpdate(userProfile);
			Long userId = userProfile.getUserProfileId();
			System.out.println("User with userId '" + userId
					+ "' has been created");
			System.out.println(userProfile);

			// Find user.
			userProfile = (UserProfile) session.get(UserProfile.class, userId);
			if (userProfile != null) {
				System.out.println("User with userId '" + userId
						+ "' has been retrieved");
				System.out.println(userProfile);
			} else {
				System.out.println("User with userId '" + userId
						+ "' has not been found");
			}
			
			// ... proceed in the same way for other entities / methods /use cases

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}

		HibernateUtil.shutdown();

	}
}