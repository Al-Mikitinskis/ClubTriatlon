package es.udc.tfg_es.clubtriatlon.model.userprofile;

import es.udc.tfg_es.clubtriatlon.model.util.dao.GenericDao;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

public interface UserProfileDao extends GenericDao<UserProfile, Long>{

    /**
     * Returns an UserProfile by login name (not user identifier)
     *
     * @param loginName the user identifier
     * @return the UserProfile
     */
    public UserProfile findByLoginName(String loginName) throws InstanceNotFoundException;
}
