package es.udc.tfg_es.clubtriatlon.web.services;
/* BSD License */

import java.io.IOException;

import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.Cookies;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;

import es.udc.tfg_es.clubtriatlon.model.UserProfile;
import es.udc.tfg_es.clubtriatlon.utils.IncorrectPasswordException;
import es.udc.tfg_es.clubtriatlon.service.UserService;
import es.udc.tfg_es.clubtriatlon.web.util.CookiesManager;
import es.udc.tfg_es.clubtriatlon.web.util.UserSession;
import es.udc.tfg_es.clubtriatlon.utils.exceptions.InstanceNotFoundException;

public class SessionFilter implements RequestFilter {

	private ApplicationStateManager applicationStateManager;
	private Cookies cookies;
	private UserService userService;

	public SessionFilter(ApplicationStateManager applicationStateManager,
			Cookies cookies, UserService userService) {

		this.applicationStateManager = applicationStateManager;
		this.cookies = cookies;
		this.userService = userService;

	}

	public boolean service(Request request, Response response,
			RequestHandler handler) throws IOException {

		if (!applicationStateManager.exists(UserSession.class)) {

			String loginName = CookiesManager.getLoginName(cookies);
			if (loginName != null) {

				String encryptedPassword = CookiesManager
						.getEncryptedPassword(cookies);
				if (encryptedPassword != null) {

					try {

						UserProfile userProfile = userService.login(loginName,
								encryptedPassword, true);
						UserSession userSession = new UserSession();
						userSession.setUserProfileId(userProfile
								.getUserProfileId());
						userSession.setName(userProfile.getName());
						applicationStateManager.set(UserSession.class,
								userSession);

					} catch (InstanceNotFoundException e) {
						CookiesManager.removeCookies(cookies);
					} catch (IncorrectPasswordException e) {
						CookiesManager.removeCookies(cookies);
					}

				}

			}

		}

		handler.service(request, response);

		return true;
	}

}
