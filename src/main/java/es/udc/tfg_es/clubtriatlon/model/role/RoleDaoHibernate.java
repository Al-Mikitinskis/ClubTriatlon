package es.udc.tfg_es.clubtriatlon.model.role;

import org.springframework.stereotype.Repository;

import es.udc.tfg_es.clubtriatlon.model.util.dao.GenericDaoHibernate;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

@Repository("RoleDao")
public class RoleDaoHibernate extends
		GenericDaoHibernate<Role, Long> implements RoleDao {

	public String getRoleNameByUserEmail(String email) throws InstanceNotFoundException {

    	Role role = (Role) getSession().createQuery(
    			"SELECT r FROM UserProfile u INNER JOIN Role r ON (u.usrId = r.userId)" +
    			"WHERE u.email = :email")
    			.setParameter("email", email)
    			.uniqueResult();
    	if (role == null) {
   			throw new InstanceNotFoundException(email, Role.class.getName());
    	} else {
    		return role.getName();
    	}

	}

}