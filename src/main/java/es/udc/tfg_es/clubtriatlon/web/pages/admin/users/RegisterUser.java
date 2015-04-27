package es.udc.tfg_es.clubtriatlon.web.pages.admin.users;
/* BSD License */

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.tfg_es.clubtriatlon.model.Role;
import es.udc.tfg_es.clubtriatlon.service.RoleService;
import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.utils.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.service.UserService;
import es.udc.tfg_es.clubtriatlon.web.pages.Index;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.web.util.UserSession;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.DuplicateInstanceException;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class RegisterUser {

	public boolean isAdmin() {
		return SecurityUtils.getSubject().hasRole("Administrador");
	}

    @Property
    private String email;

    @Property
    private String password;

    @Property
    private String retypePassword;

    @Property
    private String name;
    
    @Property
    private String birthDate;
    
    @Property
    private int phoneNumber;
    
    @Property
    private String account;
    
    @Property
	private Long roleId;

	public String getAllRoles() {
		String roleString = "";
		List<Role> list = roleService.findAllRoles();
		int i;
		for(i=0; i<list.size(); i++) {
			if (i>0) roleString += ", ";
			roleString += list.get(i).getId() + "=" + list.get(i).getName();
		}
		return roleString;
	}

    @Property
	private String role = getAllRoles();
    
    @Inject
    private RoleService roleService;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;

    @Component
    private Form registrationForm;

    @Component(id = "email")
    private TextField emailField;

    @Component(id = "password")
    private PasswordField passwordField;

    @Inject
    private Messages messages;

    private Long userProfileId;

    void onValidateFromRegistrationForm() throws InstanceNotFoundException {

        if (!registrationForm.isValid()) {
            return;
        }

        if (!password.equals(retypePassword)) {
            registrationForm.recordError(passwordField, messages
                    .get("error-passwordsDontMatch"));
        } else {

            try {
            	Role role = roleService.getRoleById(roleId);
                UserProfile userProfile = userService.registerUser(email, password,
                    new UserProfileDetails(name, birthDate, phoneNumber, account),
                    role);
                userProfileId = userProfile.getUserProfileId();
            } catch (DuplicateInstanceException e) {
                registrationForm.recordError(emailField, messages
                        .get("error-loginNameAlreadyExists"));
            }

        }

    }

    Object onSuccess() {

        userSession = new UserSession();
        userSession.setUserProfileId(userProfileId);
        userSession.setName(name);
        return Index.class;

    }

}
