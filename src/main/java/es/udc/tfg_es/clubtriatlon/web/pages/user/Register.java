package es.udc.tfg_es.clubtriatlon.web.pages.user;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.tfg_es.clubtriatlon.model.userprofile.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserService;
import es.udc.tfg_es.clubtriatlon.web.pages.Index;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.web.util.UserSession;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.DuplicateInstanceException;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Register {

    @Property
    private String loginName;

    @Property
    private String password;

    @Property
    private String retypePassword;

    @Property
    private String firstName;

    @Property
    private String lastName;

    @Property
    private String email;
    
    @Property
    private String road;
    
    @Property
    private String number;
    
    @Property
    private int postalCode;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;

    @Component
    private Form registrationForm;

    @Component(id = "loginName")
    private TextField loginNameField;

    @Component(id = "password")
    private PasswordField passwordField;

    @Inject
    private Messages messages;

    private Long userProfileId;

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        if (!password.equals(retypePassword)) {
            registrationForm.recordError(passwordField, messages
                    .get("error-passwordsDontMatch"));
        } else {

            try {
                UserProfile userProfile = userService.registerUser(loginName, password,
                    new UserProfileDetails(firstName, lastName, email, road, number, postalCode));
                userProfileId = userProfile.getUserProfileId();
            } catch (DuplicateInstanceException e) {
                registrationForm.recordError(loginNameField, messages
                        .get("error-loginNameAlreadyExists"));
            }

        }

    }

    Object onSuccess() {

        userSession = new UserSession();
        userSession.setUserProfileId(userProfileId);
        userSession.setFirstName(firstName);
        return Index.class;

    }

}
