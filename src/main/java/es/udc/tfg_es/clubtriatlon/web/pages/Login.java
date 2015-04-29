package es.udc.tfg_es.clubtriatlon.web.pages;
/* BSD License */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.utils.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.service.UserService;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.web.util.CookiesManager;
import es.udc.tfg_es.clubtriatlon.web.util.UserSession;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Login {

    @Property
    private String email;

    @Property
    private String password;

    @Property
    private boolean rememberMyPassword;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Cookies cookies;

    @Component
    private Form loginForm;

    @Inject
    private Messages messages;

    @Inject
    private UserService userService;

    private UserProfile userProfile = null;


    void onValidateFromLoginForm() {

        if (!loginForm.isValid()) {
            return;
        }

        try {
            userProfile = userService.login(email, password, false);
        } catch (InstanceNotFoundException e) {
            loginForm.recordError(messages.get("error-authenticationFailed"));
        } catch (IncorrectPasswordException e) {
            loginForm.recordError(messages.get("error-authenticationFailed"));
        }
    }

    Object onSuccess() {

    	userSession = new UserSession();
        userSession.setUserProfileId(userProfile.getUserProfileId());
        userSession.setName(userProfile.getName());

        if (rememberMyPassword) {
            CookiesManager.leaveCookies(cookies, email, userProfile
                    .getEncryptedPassword());
        }
        
        // **login de Shiro**
        //Auntentico al usuario, necesario para la autorización (roles) de páginas
        //comprobará Subject.isAuthenticated() antes de ver si tiene autorización
        UsernamePasswordToken token =
         		 new UsernamePasswordToken(userProfile.getEmail(), userProfile.getEncryptedPassword());
        Subject currentUser = SecurityUtils.getSubject();
        //token.setRememberMe(true): Por lo que entiendo, si no lo pongo y voy a otra página después de login
        //es como si no recordara el Subject... y cada vez que haga Subject.isAuthenticated() dirá que es anónimo
        token.setRememberMe(true);
        currentUser.login(token);

        return Index.class;

    }

}
