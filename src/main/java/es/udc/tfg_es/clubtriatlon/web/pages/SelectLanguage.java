package es.udc.tfg_es.clubtriatlon.web.pages;

import java.util.Locale;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PersistentLocale;

import es.udc.tfg_es.clubtriatlon.web.util.SupportedLanguages;

//@RequiresRoles("ROLE_USER")
public class SelectLanguage {

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