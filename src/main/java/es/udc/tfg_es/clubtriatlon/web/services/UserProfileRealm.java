package es.udc.tfg_es.clubtriatlon.web.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.stereotype.Service;

import es.udc.tfg_es.clubtriatlon.model.userprofile.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserService;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

//@Service("userProfileRealm")
public class UserProfileRealm extends AuthorizingRealm {
	
    @Inject
    private UserService userService;
    
//    protected final EntityManager em;

//    public UserProfileRealm(EntityManager em) {
//        super(new MemoryConstrainedCacheManager());
//        this.em = em;
//        setName("localaccounts");
//        setAuthenticationTokenClass(UsernamePasswordToken.class);
//        setCredentialsMatcher(new HashedCredentialsMatcher(Sha1Hash.ALGORITHM_NAME));
//    }

//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		
//        if (principals == null) throw new AuthorizationException("PrincipalCollection was null, which should not happen");
//        if (principals.isEmpty()) return null;
//        if (principals.fromRealm(getName()).size() <= 0) return null;
//System.out.println("1     AAbbccddEEffgghh");
//        String email = (String) principals.fromRealm(getName()).iterator().next();
//        if (email == null) return null;
//        try {
//            userService.findUserProfileByEmail(email);			
//        } catch (InstanceNotFoundException e) {
//            return null;
//	    }
//        Set<String> roles = new HashSet<String>(1);
//        try {
//            roles.add(userService.getRoleNameByUserEmail(email));			
//        } catch (InstanceNotFoundException e) {
//            roles.add("");
//        }
//System.out.println("2 AAbbccddEEffgghh");
//        return new SimpleAuthorizationInfo(roles);
//	}
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        if (principals == null) return null;
        String email = (String)principals.getPrimaryPrincipal();
        if (email == null) return null;
        try {
            userService.findUserProfileByEmail(email);			
        } catch (InstanceNotFoundException e) {
            return null;
	    }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
        	authorizationInfo.addRole(userService.getRoleNameByUserEmail(email));			
        } catch (InstanceNotFoundException e) {
            //No se añaden roles
        }
        return authorizationInfo;
    }

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		String email = upToken.getUsername();

		// Null email is invalid
		if (email == null) { throw new AccountException("Null usernames are not allowed by this realm."); }
        try {
    		UserProfile user = userService.findUserProfileByEmail(email);
System.out.println("asasass:" + user.getEmail() + "----" + user.getEncryptedPassword());
    		return new SimpleAuthenticationInfo(email, user.getEncryptedPassword(), getName());
        } catch (InstanceNotFoundException e) {
        	throw new AccountException(email + "is not a valid email.");
	    }
	}

}
