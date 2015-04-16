package es.udc.tfg_es.clubtriatlon.model.role;

import es.udc.tfg_es.clubtriatlon.model.util.dao.GenericDao;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

public interface RoleDao extends GenericDao<Role, Long>{

    /**
     * Returns the role name by email (not user identifier)
     *
     * @param email the user identifier
     * @return the Role name
     */
    public String getRoleNameByUserEmail(String email) throws InstanceNotFoundException;
}
