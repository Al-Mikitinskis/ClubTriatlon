package es.udc.tfg_es.clubtriatlon.web.pages.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.tfg_es.clubtriatlon.model.userprofile.UserProfile;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserProfileDetails;
import es.udc.tfg_es.clubtriatlon.model.userservice.UserService;
import es.udc.tfg_es.clubtriatlon.web.pages.Index;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.web.util.UserSession;
import es.udc.tfg_es.clubtriatlon.model.util.exceptions.InstanceNotFoundException;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UpdateProfile {

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

    void onPrepareForRender() throws InstanceNotFoundException {

        UserProfile userProfile;

        userProfile = userService.findUserProfile(userSession.getUserProfileId());
        firstName = userProfile.getFirstName();
        lastName = userProfile.getLastName();
        email = userProfile.getEmail();
        road = userProfile.getRoad();
        number = userProfile.getNum();
        postalCode = userProfile.getPostalCode();

    }

    Object onSuccess() throws InstanceNotFoundException {

        userService.updateUserProfileDetails(
                userSession.getUserProfileId(), new UserProfileDetails(
                        firstName, lastName, email,road, number, postalCode));
        userSession.setFirstName(firstName);
        return Index.class;

    }

}