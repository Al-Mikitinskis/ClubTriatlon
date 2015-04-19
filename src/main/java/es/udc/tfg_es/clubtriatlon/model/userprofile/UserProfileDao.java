package es.udc.tfg_es.clubtriatlon.model.userprofile;
/* BSD License */

import es.udc.tfg_es.clubtriatlon.model.util.dao.GenericDao;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

public interface UserProfileDao extends GenericDao<UserProfile, Long>{

    /**
     * Returns an UserProfile by login name (not user identifier)
     *
     * @param email the user identifier
     * @return the UserProfile
     */
    public UserProfile findByEmail(String email) throws InstanceNotFoundException;
}
