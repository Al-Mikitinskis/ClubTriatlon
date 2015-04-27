package es.udc.tfg_es.clubtriatlon.utils;
/* BSD License */

@SuppressWarnings("serial")
public class IncorrectPasswordException extends Exception {

    private String loginName;

    public IncorrectPasswordException(String loginName) {
        super("Incorrect password exception => loginName = " + loginName);
        this.loginName = loginName;
    }

    public String getEmail() {
        return loginName;
    }


}
