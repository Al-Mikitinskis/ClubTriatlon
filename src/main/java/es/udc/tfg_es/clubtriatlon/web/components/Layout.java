package es.udc.tfg_es.clubtriatlon.web.components;
/* BSD License */

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import es.udc.tfg_es.clubtriatlon.web.pages.Index;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.web.util.CookiesManager;
import es.udc.tfg_es.clubtriatlon.web.util.UserSession;

@Import(stylesheet={"context:/css/layout.css"},
        library={"context:/js/jquery-latest.js", "context:/js/menuOptions.js"})
public class Layout {
    @Property
    @Parameter(required = false, defaultPrefix = "message")
    private String menuExplanation;

    @Property
    @Parameter(required = true, defaultPrefix = "message")
    private String pageTitle;

    @Property
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Cookies cookies;

    @AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
   	Object onActionFromLogout() {
        userSession = null;
        CookiesManager.removeCookies(cookies);
        return Index.class;
	}
    
}