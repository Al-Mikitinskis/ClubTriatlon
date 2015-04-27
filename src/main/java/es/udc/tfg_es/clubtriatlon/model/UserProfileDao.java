package es.udc.tfg_es.clubtriatlon.model;
/* BSD License */

import es.udc.tfg_es.clubtriatlon.utils.dao.GenericDao;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

public interface UserProfileDao extends GenericDao<UserProfile, Long>{

    /**
     * Returns an UserProfile by login name (not user identifier)
     *
     * @param email the user identifier
     * @return the UserProfile
     */
    public UserProfile findByEmail(String email) throws InstanceNotFoundException;
}
