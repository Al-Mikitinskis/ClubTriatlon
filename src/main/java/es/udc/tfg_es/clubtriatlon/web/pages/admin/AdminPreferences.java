package es.udc.tfg_es.clubtriatlon.web.pages.admin;
/* ClubTriatlon: a web app to management of administrative work of a triathlon club
Copyright (C) 2015 Alejandro Mikitinskis

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software Foundation,
Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA

Contact here: alejandro.mikitinskis@udc.es */

import java.util.Locale;

import org.apache.shiro.SecurityUtils;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PersistentLocale;

import es.udc.tfg_es.clubtriatlon.web.pages.Index;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicy;
import es.udc.tfg_es.clubtriatlon.web.services.AuthenticationPolicyType;
import es.udc.tfg_es.clubtriatlon.web.util.SupportedLanguages;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class AdminPreferences {

	public boolean isAdmin() {
		return SecurityUtils.getSubject().hasRole("Administrador");
	}
	
    @Property
    private String language;

    @Inject
    private Locale locale;

    @Property
	private String languageList = "es=español, gl=galego";

    @Inject
    private PersistentLocale persistentLocale;

    void onPrepareForRender() {
        language = locale.getLanguage();
    }

    public String getLanguages() {
        return SupportedLanguages.getOptions(locale.getLanguage());
    }

    Object onSuccess() {

        persistentLocale.set(new Locale(language));
        return Index.class;

    }

}