package es.udc.tfg_es.clubtriatlon.test.experiments;
/* BSD License */

import org.hibernate.Transaction;

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.UserProfileDao;
import es.udc.tfg_es.clubtriatlon.model.UserProfileDaoHibernate;
import es.udc.tfg_es.clubtriatlon.utils.PasswordEncrypter;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

public class DaoExperiments {

	public static void main(String[] args) {

		UserProfileDaoHibernate userProfileDaoHibernate = new UserProfileDaoHibernate();
		userProfileDaoHibernate.setSessionFactory(HibernateUtil
				.getSessionFactory());
		UserProfileDao userProfileDao = userProfileDaoHibernate;

		Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession()
				.beginTransaction();
		try {
			Role role = new Role("user");
			// Register user.
			UserProfile userProfile = new UserProfile("daoUser",
					PasswordEncrypter.crypt("userPassword"), "name",
					"1980/01/23", 601601601, "account", role);
			userProfileDao.save(userProfile);
			Long userId = userProfile.getUserProfileId();
			System.out.println("User with userId '" + userId
					+ "' has been created");
			System.out.println(userProfile);

			// Find user.
			userProfile = userProfileDao.find(userId);
			System.out.println("User with userId '" + userId
					+ "' has been retrieved");
			System.out.println(userProfile);
			
			// ... proceed in the same way for other entities / methods / use cases

			tx.commit();

		} catch (RuntimeException e) {
			e.printStackTrace();
			tx.rollback();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			tx.commit();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

		HibernateUtil.shutdown();

	}

}
