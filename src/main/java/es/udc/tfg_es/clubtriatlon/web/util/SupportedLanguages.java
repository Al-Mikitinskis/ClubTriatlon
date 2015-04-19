package es.udc.tfg_es.clubtriatlon.web.util;
/* BSD License */

import java.util.HashMap;
import java.util.Map;

public class SupportedLanguages {

    private static Map<String, String> options;

    private static String codes = "";

    private SupportedLanguages() {}

    public static void initialize() {

        /*
         * For *simplicity*, the following variables ("options_xx") contain
         * the language code and names in the three languages supported by
         * pojo-minibank. Furthermore, in each variable the list of codes and
         * names are specified in the format required by the Tapestry's "Select"
         * component.
         *
         * In a *real-life* application, this method could take a Spring
         * service (a model facade) as a parameter for reading the language
         * codes and names from the database:
         *
         * public static void initialize(LanguageService languageService) { ... }
         *
         * After reading the codes and names, it would create dynamically the
         * strings in the format required by Tapestry's "Select" component and
         * insert them in the "options" map.
         *
         * In *any* case, note that the "options" map acts as a cache and that
         * contains the strings in the format required by Tapestry's "Select"
         * component.
         *
         */
        String options_es = "es=Español, gl=Gallego";
        String options_gl = "es=Español, gl=Galego";

        options = new HashMap<String, String>();
        options.put("es", options_es);
        options.put("gl", options_gl);

        codes = "es,gl";

    }

    public static String getCodes() {
        return codes;
    }

    public static String getOptions(String languageCode) {

        String languages = options.get(languageCode);

        if (languages != null) {
            return languages;
        } else {
            return options.get("es");
        }

    }

}
