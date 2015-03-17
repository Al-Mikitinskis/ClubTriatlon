package es.udc.tfg_es.clubtriatlon.model.userprofile;

import org.springframework.stereotype.Repository;

import es.udc.tfg_es.clubtriatlon.model.util.dao.GenericDaoHibernate;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

@Repository("userProfileDao")
public class UserProfileDaoHibernate extends
		GenericDaoHibernate<UserProfile, Long> implements UserProfileDao {

	public UserProfile findByLoginName(String loginName) throws InstanceNotFoundException {

    	UserProfile userProfile = (UserProfile) getSession().createQuery(
    			"SELECT u FROM UserProfile u WHERE u.loginName = :loginName")
    			.setParameter("loginName", loginName)
    			.uniqueResult();
    	if (userProfile == null) {
   			throw new InstanceNotFoundException(loginName, UserProfile.class.getName());
    	} else {
    		return userProfile;
    	}

	}

}